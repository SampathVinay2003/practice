package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumGrid {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-->0){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            long[][] grid = new long[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    grid[i][j] = scanner.nextLong();
                }
            }
            long[][] dp = new long[n+1][m+1];
            for(long[] d:dp) Arrays.fill(d,-1);
            System.out.println(solve(dp, grid, n, m));
        }
    }
    public static long solve(long[][] dp, long[][] grid, int n, int m){
        if(n==0 || m==0) return 0;
        if(dp[n][m]!=-1) return dp[n][m];
        return dp[n][m] = Math.max(solve(dp, grid, n-1, m), solve(dp, grid, n, m-1))+grid[n-1][m-1];
    }
}
