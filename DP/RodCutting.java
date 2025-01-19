package DP;

public class RodCutting {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static void rodCutting() {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = 8; // Rod length

        int[][] dp = new int[prices.length + 1][n + 1];

        // Fill the table
        for (int i = 0; i <= prices.length; i++) {
            for (int length = 0; length <= n; length++) {
                if (i == 0 || length == 0) {
                    dp[i][length] = 0; // Base case
                } else if (i <= length) {
                    dp[i][length] = max(prices[i - 1] + dp[i][length - i], dp[i - 1][length]);
                } else {
                    dp[i][length] = dp[i - 1][length];
                }
            }
        }

        // Print the DP table
        System.out.println("DP Table:");
        for (int i = 0; i <= prices.length; i++) {
            for (int length = 0; length <= n; length++) {
                System.out.print(dp[i][length] + " ");
            }
            System.out.println();
        }

        // Maximum profit is in dp[prices.length][n]
        System.out.println("Maximum Profit: " + dp[prices.length][n]);
    }

    public static void main(String[] args) {
        rodCutting();
    }
}
