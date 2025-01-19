package DP;

public class SubsetSum {

    static int isSubsetSum(int[] set, int n, int target) {
        // Create a DP table
        int[][] dp = new int[n + 1][target + 1];

        // Base case: A sum of 0 is always possible with an empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // 1 indicates true
        }

        // Base case: If the set is empty and the sum is non-zero, it's not possible
        for (int j = 1; j <= target; j++) {
            dp[0][j] = 0; // 0 indicates false
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (set[i - 1] <= j) {
                    // Include the current element or exclude it
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - set[i - 1]]);
                } else {
                    // Exclude the current element
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Print the DP table for visualization
        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // Return the result
        return dp[n][target];
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int target = 9;

        int result = isSubsetSum(set, set.length, target);
        System.out.println("Is subset with sum " + target + " possible? " + (result == 1));
    }
}
