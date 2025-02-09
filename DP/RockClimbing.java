package DP;

import java.util.ArrayList;
import java.util.List;

public class RockClimbing {

    static int[][] dp;

    static int rockClimbing(int[][] wall) {
        int rows = wall.length;
        int cols = wall[0].length;

        // Create a DP table with an extra row and columns for boundaries
        dp = new int[rows + 1][cols + 2];

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
        int minIndex = 1;
        for (int j = 1; j <= cols; j++) {
            if (dp[rows][j] < minDanger) {
                minDanger = dp[rows][j];
                minIndex = j;
            }
        }

        // Print the DP table
        System.out.println("DP Table:");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // Find and print the optimal path
        List<int[]> path = traceback(rows, minIndex);
        System.out.println("Optimal Path:");
        for (int[] step : path) {
            System.out.println("(" + step[0] + ", " + step[1] + ")");
        }

        return minDanger;
    }

    static List<int[]> traceback(int row, int col) {
        List<int[]> path = new ArrayList<>();
        int rows = dp.length - 1;
        int cols = dp[0].length - 2;

        int i = row;
        int j = col;

        while (i > 0) {
            path.add(new int[]{i, j}); // Store current position

            // Move to the previous row by choosing the best parent
            if (j > 1 && dp[i - 1][j - 1] == Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1]))) {
                j = j - 1;
            } else if (j < cols && dp[i - 1][j + 1] == Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1]))) {
                j = j + 1;
            }
            i--;
        }

        // Reverse path since we traced from bottom to top
        List<int[]> reversedPath = new ArrayList<>();
        for (int k = path.size() - 1; k >= 0; k--) {
            reversedPath.add(path.get(k));
        }

        return reversedPath;
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
