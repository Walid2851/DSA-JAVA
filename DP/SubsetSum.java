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
        int[] set = {2,5,3,6};
        int target = 10;

        int result = isSubsetSum(set, set.length, target);
        System.out.println("Is subset with sum " + target + " possible? " + (result == 1));
    }
}

// package DP;

// import java.util.Arrays;

// public class SubsetSum {
//     private int[][] dp;

//     private boolean func(int i, int sum, int[] nums) {
//         if (sum == 0) return true; // If the sum is 0, we found a valid subset
//         if (i < 0) return false;   // If no elements left, return false
//         if (dp[i][sum] != -1) return dp[i][sum] == 1; // Memoized result

//         // Not considering the current index
//         boolean isPossible = func(i - 1, sum, nums);

//         // Considering the current index
//         if (sum >= nums[i]) {
//             isPossible |= func(i - 1, sum - nums[i], nums);
//         }

//         dp[i][sum] = isPossible ? 1 : 0; // Store result in DP table
//         return isPossible;
//     }

//     public boolean subsetSumExists(int[] nums, int sum) {
//         dp = new int[nums.length][sum + 1];

//         for (int[] row : dp) {
//             Arrays.fill(row, -1); // Initialize DP table with -1
//         }

//         return func(nums.length - 1, sum, nums);
//     }

//     public static void main(String[] args) {
//         SubsetSum ss = new SubsetSum();
//         int[] nums = {1, 3, 4};
//         int sum = 5;
//         boolean ans = ss.subsetSumExists(nums, sum);
//         System.out.println("Subset sum exists? " + ans);
//     }
// }
