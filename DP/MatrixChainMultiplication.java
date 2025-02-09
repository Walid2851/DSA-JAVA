package DP;

public class MatrixChainMultiplication {

    static void printOptimalParens(int[][] split, int i, int j, StringBuilder result) {
        if (i == j) {
            result.append("A").append(i);
            return;
        }
        result.append("(");
        printOptimalParens(split, i, split[i][j], result);
        printOptimalParens(split, split[i][j] + 1, j, result);
        result.append(")");
    }

    static int matrixChainOrder(int[] dims) {
        int n = dims.length; // Number of matrices + 1
        int[][] m = new int[n][n]; // DP table
        int[][] km = new int[n][n]; // To store splits for parenthesization

        for (int d = 1; d < n - 1; d++) {
            for (int i = 1; i < n - d; i++) {
                int j = i + d;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k + 1][j] + dims[i - 1] * dims[k] * dims[j];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        km[i][j] = k; // Store split point
                    }
                }
            }
        }

        //print m
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println();
        //print km
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(km[i][j]+" ");
            }
            System.out.println();
        }

        // Construct optimal parenthesization
        StringBuilder result = new StringBuilder();
        printOptimalParens(km, 1, n - 1, result);
        System.out.println("Optimal Parenthesization: " + result);

        return m[1][n - 1];
    }

    public static void main(String[] args) {
        int[] dims = {10, 15, 5, 6, 8}; // Dimensions of matrices

        int minCost = matrixChainOrder(dims);
        System.out.println("Minimum number of scalar multiplications: " + minCost);
    }
}