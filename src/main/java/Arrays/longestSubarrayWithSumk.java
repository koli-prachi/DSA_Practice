package Arrays;

/*

PROBLEM : Longest Subarray with Sum 'k'
PLATFORM : GFG
APPRAOCH :
    1. Brute Force => Check all subarrays  Time: O(n²) | Space : O(1) => no extra data structure
    2. Sliding Window (ONLY positive numbers) => Using left and right pointers  Time : O(n) | Space : O(1)
    3. Prefix Sum + HashMap (General Case:- includes +ve, -ve, 0) => Better than sliding window  Time : O(n) | Space : O(n)

KEY LEARNINGS:
   - sliding window works only for non-negative arrays
   - prefix sum is used for general case
        - (prefixSum - k) helps find out the valid subarray
        - HashMap helps to store the first occurence of prefix sum

*/


import java.util.HashMap;

public class longestSubarrayWithSumk {


    public static void main(String[] args){

        int[] arr = {5, 2, 2, 5, 1, 1, 1, 1, 4};
        int k = 4;
        System.out.println(" longest Subarray Optimal " + longestSubarrayOptimal(arr, k));
        System.out.println(" longest Subarray Optimal Positive " + longestSubarrayOptimalPositive(arr, k));
        System.out.println(" longest Subarray Brute " + longestSubarrayBrute(arr, k));
    }

    // BRUTE FORCE APPROACH
    public static int longestSubarrayBrute(int[] arr, int k){

        int maxLength = 0;

        for (int i = 0; i < arr.length; i++){

            int sum = 0;  // for every i iteration sum should start from 0
            for (int j = i; j < arr.length; j++){

                // helps to get sum of each and every subarray possible
                sum += arr[j];

                // checks if the sum is equal to 'k'
                if (sum == k){
                    maxLength = Math.max(maxLength, (j - i + 1));
                }
            }
        }

        return maxLength;
    }

    // Longest subrray With Sum 'k' => Sliding Window Approach (works for only +ve elements)
    public static int longestSubarrayOptimalPositive(int[] arr, int k){

        // declaring variables
        int leftPointer = 0;
        int rightPointer = 0;
        int sum = 0;
        int maxLength = 0;

        // maintaining a window
        for (rightPointer = 0; rightPointer < arr.length; rightPointer++) {

                sum += arr[rightPointer]; // expanding the window to right

                // if the sum gets too big than the 'k' => THEN Shrink the window till it is smaller than 'k'
                while (sum > k && leftPointer <= rightPointer) {
                    sum -= arr[leftPointer];
                    leftPointer++;
                }

                // if sum matches the 'k' => THEN we have found the required subarray so find its length to get maxLength
                if (sum == k) {
                    maxLength = Math.max(maxLength, (rightPointer - leftPointer + 1));
                }

        }


        return maxLength;
    }


    // Longest subrray With Sum 'k' => Using PREFIX SUM + HashMap (works for both +ve and -ve elements)
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
