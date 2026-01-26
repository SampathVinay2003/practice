package LeetCode.dp;

import java.util.Arrays;

public class MinCost {
    public static void main(String[] args) {
        int cuts[] = {1, 3};
        System.out.println(minCost(cuts));
    }

    public static int minCost(int[] cuts) {
        int n = 7; // or pass it as argument if this is a general method
        Arrays.sort(cuts);
        int m = cuts.length;

        int[] p = new int[m + 2];
        p[0] = 0;
        for (int i = 0; i < m; i++) p[i + 1] = cuts[i];
        p[m + 1] = n;

        int[][] dp = new int[m + 2][m + 2];
        for (int i = 0; i < m + 2; i++) Arrays.fill(dp[i], -1);

        return solve(0, m + 1, p, dp);
    }

    // solve(i, j): min cost to cut between p[i] and p[j]
    private static int solve(int i, int j, int[] p, int[][] dp) {
        if (j == i + 1) return 0;          // base case: no cut inside

        if (dp[i][j] != -1) return dp[i][j];

        int ans = Integer.MAX_VALUE / 2;
        // try all possible first cuts k between i and j
        for (int k = i + 1; k <= j - 1; k++) {
            int left = solve(i, k, p, dp);
            int right = solve(k, j, p, dp);
            int cost = (p[j] - p[i]) + left + right;
            ans = Math.min(ans, cost);
        }

        if (ans == Integer.MAX_VALUE / 2) ans = 0; // in case there are no k (but actually base case handles it)
        return dp[i][j] = ans;
    }
}