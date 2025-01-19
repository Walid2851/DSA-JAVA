package DP;

public class RockClimbing {

    static int rockClimbing(int[][] wall) {
        int rows = wall.length;
        int cols = wall[0].length;

        // Create a DP table with an extra row and columns for boundaries
        int[][] dp = new int[rows + 1][cols + 2];

        // Initialize boundary conditions (infinite danger on the edges)
        for (int j = 0; j <= cols + 1; j++) {
            dp[0][j] = 0; // Ground level
        }
        for (int i = 1; i <= rows; i++) {
            dp[i][0] = Integer.MAX_VALUE; // Left boundary
            dp[i][cols + 1] = Integer.MAX_VALUE; // Right boundary
        }

        // Fill the DP table
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                dp[i][j] = wall[i - 1][j - 1] + Math.min(
                        dp[i - 1][j - 1], // Top-left
                        Math.min(dp[i - 1][j], dp[i - 1][j + 1]) // Top-middle and top-right
                );
            }
        }

        // Find the minimum value in the last row
        int minDanger = Integer.MAX_VALUE;
        for (int j = 1; j <= cols; j++) {
            minDanger = Math.min(minDanger, dp[rows][j]);
        }

        // Print the DP table
        System.out.println("DP Table:");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return minDanger;
    }

    public static void main(String[] args) {
        int[][] wall = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int minDanger = rockClimbing(wall);
        System.out.println("Minimum danger path rating: " + minDanger);
    }
}
