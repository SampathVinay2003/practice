package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class MaxAreaOfSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            ans = 0;
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            int[][] dp = new int[n][m];
            for (int[] d : dp) Arrays.fill(d, -1);
            solve(dp, grid, n - 1, m - 1);
            System.out.println(ans*ans);
        }
    }

    public static int ans;

    public static int solve(int[][] dp, int[][] grid, int n, int m) {
        for(int i=0;i<=n;i++){
            dp[i][0] = grid[i][0];
            ans = Math.max(dp[i][0],ans);
        }
        for(int i=0;i<=m;i++){
            dp[0][i] = grid[0][i];
            ans = Math.max(dp[0][i],ans);
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(grid[i][j]==1){
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
                }else{
                    dp[i][j] = 0;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
