package Arrays;

/*

PROBLEM : 58. Length of Last Word
PLATFORM : GFG
APPRAOCH :
    1. Brute Force => Using trim()  Time: O(n) | Space : O(n)
    2. Best Optimal => Using 2 while loops  Time : O(n) | Space : O(1)

KEY LEARNINGS:
    focus on skippind the trailing spaces, then counting the last word

*/


import java.util.HashMap;

public class longestSubarrayWithSumk {


    public static void main(String[] args){

        int[] arr = {5, 2, 2, 5, 1, 1, 1, 1, 4};
        int k = 4;
        System.out.println(longestSubarrayOptimal(arr, k));
    }


    // Longest subrray With Sum 'k' => Using PREFIX SUM (works for both +ve and -ve elements)
    public static int longestSubarrayOptimal(int[] arr, int k){

        // HashMap for storing the <key, value> = <prefixSum, FirsSeenIndex> | Storing the first occurence of the prefix sum to find the valid subarray that sums to 'k'
        HashMap<Integer, Integer> map = new HashMap<>();

        // declaring variables
        int prefixSum = 0;
        int result = 0;   // longest subarray sum with 'k'

        // loop for trversing the array
        for(int i = 0; i < arr.length; i++){

            // finding the prefixSum
            prefixSum += arr[i];

            // Handling the edge case => if sum from index 0 to index i is k  |  arr = [10, 5], k = 15  |  [10, 5] → whole subarray = k  |  length = i + 1 = 2
            if(prefixSum == k){
                result = Math.max(result,(i + 1));
            }

            // Checking if the (remaining = prefixSum - k) is there in the map as key
            // To find the key in map => map.containsKey()
            // To retrieve the value of that key => map.get(key)
            if (map.containsKey(prefixSum - k)){
                // store the max subarray in rssult
                result = Math.max(result, i - (map.get(prefixSum - k))); // length will be current index - previous index
            }

            // if we do not get the prefixSum in the map THEN we add the prefixSum of first Occurence
            if(!map.containsKey(prefixSum)){
                // To add the <key, value> => map.put() | We are storing the prefixSum and the index at which it occurs firstly
                map.put(prefixSum, i);
            }

        }

        return result;
    }
}
