package DP;

public class CoinChange {

    static int coinChangeWays(int[] coins, int n, int amount) {
        // Create a 2D table where dp[i][j] represents the number of ways to make amount j using the first i coins
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: There is one way to make amount 0 (use no coins)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the table in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    // Include the current coin + exclude the current coin
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                } else {
                    // Exclude the current coin
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for(int i=0;i<=n;i++){
            for(int j=0;j<=amount;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        // The result is in dp[n][amount], the number of ways to make the amount using all n coins
        return dp[n][amount];
    }

    public static void main(String[] args) {
        int[] coins = {2,5,3,6};
        int amount = 10;
        int n = coins.length;

        int ways = coinChangeWays(coins, n, amount);
        System.out.println("Number of ways to make change: " + ways);
    }
}
// package DP;
// import java.util.Arrays;

// class CoinChange {
//     private int[][] dp;
    
//     private int func(int ind, int amount, int[] coins) {
//         if (amount == 0) return 1;
//         if (ind < 0) return 0;
//         if (dp[ind][amount] != -1) return dp[ind][amount];
        
//         int ways = 0;
//         for (int coinAmount = 0; coinAmount <= amount; coinAmount += coins[ind]) {
//             ways += func(ind - 1, amount - coinAmount, coins);
//         }
        
//         return dp[ind][amount] = ways;
//     }
    
//     public int change(int amount, int[] coins) {
//         dp = new int[coins.length][amount + 1];
//         for (int[] row : dp) {
//             Arrays.fill(row, -1);
//         }
//         return func(coins.length - 1, amount, coins);
//     }

//     public static void main(String[] args) {
//         CoinChange cn = new CoinChange();
//         int amount = 8;
//         int[] coins = {1,3,5};
//         System.out.println("Ways to make change: " + cn.change(amount, coins));
//     }
// }
