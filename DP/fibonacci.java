package DP;

import java.util.HashMap;
import java.util.Scanner;

public class fibonacci {

    // Memoization approach
    static int fibMemo(int n, HashMap<Integer, Integer> memo) {
        if (n < 2) return n;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // Tabulation approach
    static int fibTab(int n) {
        if (n < 2) return n;

        int[] dp = new int[n + 1]; 
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        int ansMemo = fibMemo(n, memo);
        System.out.println("fibMemo: " + ansMemo);

        int ansTab = fibTab(n);
        System.out.println("fibTab: " + ansTab);
    }
}
