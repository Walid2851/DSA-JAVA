package DP;

import java.util.Scanner;

public class fibonacci {

    public static int fib(int n,int dp[]){


        if(n<2) return n;
        if(dp[n] != -1) return dp[n];
        return fib(n-1,dp) + fib(n-2,dp);
    }

    public static void main(String args[]){
        final int N = 100005;
        int dp[] = new int[N];
        for(int i=0;i<N;i++)dp[i]=-1;

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println(fib(x,dp));
    }
}
