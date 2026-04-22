package Strings;

/*

PROBLEM : 58. Length of Last Word
PLATFORM : Leetcode
APPRAOCH :
    1. Brute Force => Using trim()  Time: O(n) | Space : O(n)
    2. Best Optimal => Using 2 while loops  Time : O(n) | Space : O(1)

KEY LEARNINGS:
    focus on skippind the trailing spaces, then counting the last word

*/

public class lengthOfLastWord {

    public static void main(String[] args){

        String s = "    Fly    to  the  moon        ";

        // BRUTE force APPROACH
        System.out.println("lengthOfLastWordBrute : " + lengthOfLastWordBrute(s));

        // BEST OPTIMAL APPROACH
        System.out.println("lengthOfLastWordOptimal : " + lengthOfLastWordOptimal(s));
    }


    // BRUTE force APPROACH => trim() creates one more sting increasing the space complexity => O(n)
    public static int lengthOfLastWordBrute(String s){

        // Using trim() to remove trailing & leading spaces
        String str = s.trim();

        int count = 0;

        // loop that starts form end and counts the length of last word
        for(int i = str.length() - 1; i >= 0; i--){

            if(str.charAt(i) != ' '){
                count++;
            } else {
                break; // breaking the loop after counting the length
            }
        }

        return count;
    }

    // BEST OPTIMAL APPROACH => Using reverse traversal (single pointer)
    // First skip trailing spaces, then count characters of last word
    public static int lengthOfLastWordOptimal(String s){

        int count = 0;
        int i = s.length() - 1; // staring from the last of string

        // skipping the trailing spaces
        while(i >= 0 && s.charAt(i) == ' '){
            i--; // moving the pointer to the left
        }

        // counts the length
        while(i >= 0 && s.charAt(i) != ' '){
            count++;
            i--;
        }

        return count;
    }
}
