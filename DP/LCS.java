package DP;

import java.util.Arrays;

public class LCS {
    int[][] dp;

    // Recursive function with memoization (starting from index 0)
    int lcs(int i, int j, String s1, String s2) {
        // Base case: If either string is exhausted, return 0
        if (i == s1.length() || j == s2.length()) return 0;

        // Return precomputed result
        if (dp[i][j] != -1) return dp[i][j];

        int result;
        if (s1.charAt(i) == s2.charAt(j)) {
            result = 1 + lcs(i + 1, j + 1, s1, s2); // If characters match
        } else {
            result = Math.max(lcs(i + 1, j, s1, s2), lcs(i, j + 1, s1, s2)); // If characters don’t match
        }

        return dp[i][j] = result; // Store result in dp table
    }

    // Wrapper function
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        dp = new int[n][m];

        // Fill DP table with -1
        for (int[] row : dp) Arrays.fill(row, -1);

        // Call LCS function starting from index 0
        return lcs(0, 0, s1, s2);
    }

    // Driver function
    public static void main(String args[]) {
        LCS solution = new LCS();
        String s1 = "bd";
        String s2 = "abcd";

        int lengthOfLCS = solution.longestCommonSubsequence(s1, s2);
        System.out.println("Length of LCS: " + lengthOfLCS);
    }
}



// class LCS3 {
//     // Recursive function to find LCS of three strings
//     static int lcs3(int i, int j, int k, String s1, String s2, String s3) {
//         // Base case: If any string is exhausted, return 0
//         if (i < 0 || j < 0 || k < 0) return 0;

//         // If all characters match, include this character in LCS
//         if (s1.charAt(i) == s2.charAt(j) && s2.charAt(j) == s3.charAt(k)) {
//             return 1 + lcs3(i - 1, j - 1, k - 1, s1, s2, s3);
//         }

//         // Otherwise, take the maximum LCS by excluding one string at a time
//         return Math.max(
//             Math.max(lcs3(i - 1, j, k, s1, s2, s3), lcs3(i, j - 1, k, s1, s2, s3)),
//             lcs3(i, j, k - 1, s1, s2, s3)
//         );
//     }

//     public static void main(String[] args) {
//         String s1 = "abc";
//         String s2 = "ac";
//         String s3 = "bca";

//         int lcsLength = lcs3(s1.length() - 1, s2.length() - 1, s3.length() - 1, s1, s2, s3);
//         System.out.println("Length of LCS of three strings: " + lcsLength);
//     }
// }


// package DP;

// public class LCS {

//     static int lcs(String s1, String s2) {
//         int n = s1.length();
//         int m = s2.length();

//         // Create a 2D array to store lengths of LCS
//         int[][] D = new int[n + 1][m + 1];

//         // Initialize the base cases: If either string is empty, LCS length is 0
//         for (int i = 0; i <= n; i++) D[i][0] = 0;
//         for (int j = 0; j <= m; j++) D[0][j] = 0;

//         // Fill the DP table
//         for (int i = 1; i <= n; i++) {
//             for (int j = 1; j <= m; j++) {
//                 if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
//                     // If characters match, take diagonal value and add 1
//                     D[i][j] = D[i - 1][j - 1] + 1;
//                 } else {
//                     // Otherwise, take the maximum of top or left cell
//                     D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
//                 }
//             }
//         }

//         // Return the length of LCS (bottom-right cell)
//         return D[n][m];
//     }

//     public static void main(String[] args) {
//         String s1 = "AGGTAB";
//         String s2 = "GXTXAYB";

//         // Calculate the length of LCS
//         int lengthOfLCS = lcs(s1, s2);

//         // Print the result
//         System.out.println("Length of LCS: " + lengthOfLCS);
//     }
// }
