package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-->0){
            int n = scanner.nextInt();
            int w = scanner.nextInt();
            int[][] wts = new int[n][2];
            for(int i=0;i<n;i++){
                wts[i][0] = scanner.nextInt();
                wts[i][1] = scanner.nextInt();
            }
            int[][] dp = new int[n+1][w+1];
            for(int[] d:dp) Arrays.fill(d,-1);
            System.out.println(solve(dp, wts, n, w));
        }
    }
    public static int solve(int[][] dp, int[][] wts, int n , int w){
        if(n==0 || w==0) return 0;
        if(dp[n][w]!=-1) return dp[n][w];
        if(wts[n-1][0]>w) return dp[n][w] = solve(dp, wts, n-1, w);
        return dp[n][w] = Math.max(solve(dp, wts, n-1, w), solve(dp, wts, n-1, w-wts[n-1][0])+wts[n-1][1]);
    }
}
