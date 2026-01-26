package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class BricksColor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int[][] dp = new int[300][300];

            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }

            System.out.println(solve(n, m, k, 0, 0, dp));
        }
    }

    static final int MOD = 1_000_000_007;

    public static int solve(int n, int m, int k, int level, int breaks, int[][] dp) {
        if (level == n) return breaks == k ? 1 : 0;
        if (breaks > k) return 0;
        if (dp[level][breaks] != -1) return dp[level][breaks];
        long ans = 0;

        if (level == 0) {
            ans += (long) m * solve(n, m, k, level + 1, breaks, dp);
        } else {
            ans += solve(n, m, k, level + 1, breaks, dp) + (long) (m - 1) * solve(n, m, k, level + 1, breaks + 1, dp);
        }
        if (ans >= MOD) ans %= MOD; // if you keep this brute force, at least mod it

        return dp[level][breaks] = (int) (ans % MOD);
    }

}
