package algoZenith.dp;

import java.util.*;

public class RemoveMarbles {
    static int[] a;
    static int n;
    static int[][][] dp; // dp[l][r][k]

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        dp = new int[n][n][n + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(dp[i][j], -1);

        System.out.println(solve(0, n - 1, 0));
    }

    // f(l, r, k): max points for a[l..r] with k same-colored marbles as a[l] carried from the left
    static int solve(int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] != -1) return dp[l][r][k];

        // compress consecutive equals at the front into k
        int L = l;
        while (L + 1 <= r && a[L + 1] == a[l]) {
            L++;
            k++;
        }
        l = L;

        // Option 1: remove the (k+1)-block at l now
        int best = (k + 1) * (k + 1) + solve(l + 1, r, 0);

        // Option 2: merge with a later occurrence of the same color
        for (int m = l + 1; m <= r; m++) {
            if (a[m] == a[l]) {
                // clear the middle so l and m become adjacent, then carry k+1 forward
                int cand = solve(l + 1, m - 1, 0) + solve(m, r, k + 1);
                if (cand > best) best = cand;
            }
        }

        return dp[l][r][k] = best;
    }
}
