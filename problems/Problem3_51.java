package problems;

import java.util.Scanner;

public class Problem3_51{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read grid dimensions
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Read the grid
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] row = scanner.nextLine().split(",");
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }

        // Calculate and print the perimeter
        System.out.println(calculatePerimeter(grid));
    }

    public static int calculatePerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;

        // Iterate over each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    // Each land cell starts with 4 sides
                    perimeter += 4;

                    // Check and subtract sides for neighboring land cells
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2; // Top neighbor
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2; // Left neighbor
                    }
                }
            }
        }

        return perimeter;
    }
}

