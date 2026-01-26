package CFPractice;
import java.io.*;
import java.util.*;

public class ClearString {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();
        System.out.println(minDeleteOps(s));
    }

    // dp[l][r] = minimal operations to delete s[l..r]
    private static int minDeleteOps(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;

                // baseline: delete a[r] alone after clearing [l..r-1]
                int best = dp[l][r - 1] + 1;

                // try merging a[r] with some a[k] == a[r]
                for (int k = l; k < r; k++) {
                    if (a[k] == a[r]) {
                        // clear the middle so a[k] and a[r] become adjacent
                        int middle = (k + 1 <= r - 1) ? dp[k + 1][r - 1] : 0;
                        best = Math.min(best, dp[l][k] + middle);
                    }
                }
                dp[l][r] = best;
            }
        }
        return dp[0][n - 1];
    }
}
