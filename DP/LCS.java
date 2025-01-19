package DP;

import java.util.HashMap;

public class LCS {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int lcs(int i, int j, String s1, String s2, HashMap<String, Integer> memo) {
        // Base case: If either string is exhausted, return 0
        if (i >= s1.length() || j >= s2.length()) return 0;

        // Create a unique key for the current state
        String key = i + "," + j;

        // If the result is already computed, return it
        if (memo.containsKey(key)) return memo.get(key);

        int result;

        // Check if characters match
        if (s1.charAt(i) == s2.charAt(j)) {
            result = 1 + lcs(i + 1, j + 1, s1, s2, memo);
        } else {
            // If characters do not match, consider two options
            result = max(lcs(i + 1, j, s1, s2, memo), lcs(i, j + 1, s1, s2, memo));
        }

        // Store the result in the memoization table
        memo.put(key, result);

        return result;
    }

    public static void main(String args[]) {
        String s1 = "bd";
        String s2 = "abcd";

        // Memoization table
        HashMap<String, Integer> memo = new HashMap<>();

        // Calculate LCS
        int lengthOfLCS = lcs(0, 0, s1, s2, memo);

        // Print the result
        System.out.println("Length of LCS: " + lengthOfLCS);
    }
}
