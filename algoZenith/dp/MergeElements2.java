package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class MergeElements2 {
    static int x, y, z;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        x = scanner.nextInt();
        y = scanner.nextInt();
        z = scanner.nextInt();
        int[] a = new int[n];
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            prefix[i + 1] = (prefix[i] + a[i]) % 100;
        }
        int[][] dp = new int[n][n];
        for (int[] d : dp) Arrays.fill(d, -1);
        System.out.println(solve(dp, a, 0, n - 1, prefix));
    }

    public static int solve(int[][] dp, int[] a, int l, int r, int[] prefix) {
        if (l >= r) return 0;
        if (l == r - 1) {
            return a[l] * a[r];
        }
        if (dp[l][r] != -1) return dp[l][r];
        int ans = Integer.MAX_VALUE;
        for (int i = l; i < r; i++) {
            int a1 = solve(dp, a, l, i, prefix);
            int a2 = solve(dp, a, i + 1, r, prefix);
            int c = color(prefix, l, i);
            int d = color(prefix, i + 1, r);
            ans = Math.min(ans, a1 + a2 + c * d);

        }
        return dp[l][r] = ans;
    }

    private static int color(int[] pref, int l, int r) {
        int x = pref[r + 1] - pref[l];
        x %= 100;
        if (x < 0) x += 100;
        return x;
    }
}
