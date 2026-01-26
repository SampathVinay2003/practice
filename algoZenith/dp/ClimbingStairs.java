package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class ClimbingStairs {
    static long[][] dp;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            dp = new long[n+1][m+1];
            for(long[]d : dp) Arrays.fill(d, -1L);
            System.out.println(solve(n, m));
        }
    }

    public static long solve(int n, int m) {
        if (n < 0) return Integer.MAX_VALUE/3;
        if(n == 0)return 0;
        if(dp[n][m] != -1)return dp[n][m];
        return dp[n][m] = Math.min(1+solve(n-m, m), 1+solve(n-1, m));
    }
}
