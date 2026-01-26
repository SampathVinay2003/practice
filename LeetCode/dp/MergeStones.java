package LeetCode.dp;

import java.util.*;

public class MergeStones {

    static int[] prefix;
    static int k, n;
    static int[][][] dp;

    static final int INF = (int)1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();

        int[] arr = new int[n];
        prefix = new int[n+1];

        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
            prefix[i+1] = prefix[i] + arr[i];
        }

        // Feasibility check
        if ((n - 1) % (k - 1) != 0) {
            System.out.println(-1);
            return;
        }

        dp = new int[n][n][k+1];
        for (int[][] d2 : dp)
            for (int[] d1 : d2)
                Arrays.fill(d1, -1);

        System.out.println( solve(0, n-1, 1) );
    }

    static int sum(int l, int r) {
        return prefix[r+1] - prefix[l];
    }

    // dp[l][r][p] = min cost to merge l..r into p piles
    static int solve(int l, int r, int p) {

        if (dp[l][r][p] != -1) return dp[l][r][p];

        int len = r - l + 1;

        // impossible cases
        if (p > len) return dp[l][r][p] = INF;

        // base case
        if (l == r) {
            return dp[l][r][p] = (p == 1 ? 0 : INF);
        }

        // Case 1: p > 1 (recursive splitting)
        int res = INF;

        if (p > 1) {
            // We must make left part into 1 pile, right into p-1 piles
            for (int m = l; m < r; m += (k - 1)) {
                int left = solve(l, m, 1);
                int right = solve(m+1, r, p-1);
                res = Math.min(res, left + right);
            }
        }

        // Case 2: p == 1
        else {
            // Can we merge l..r into 1 pile?
            if ((len - 1) % (k - 1) == 0) {
                // First form exactly k piles
                int tmp = solve(l, r, k);
                if (tmp < INF)
                    res = tmp + sum(l, r);
            } else {
                res = INF;
            }
        }

        return dp[l][r][p] = res;
    }
}
