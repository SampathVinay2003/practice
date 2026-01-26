package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class NumberOfPaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] grid = new int[n][m];
            long[][] dp = new long[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }
            System.out.println(solve(dp, n, m, grid));
        }
    }

    public static long solve(long[][] dp, int n, int m, int[][] grid) {
        for (int i = 0; i < m; i++) {
            if (grid[0][i] == 0) dp[0][i] = 1;
            else break;
        }
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 0) dp[i][0] = 1;
            else break;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
