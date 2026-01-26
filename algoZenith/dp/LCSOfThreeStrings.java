package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class LCSOfThreeStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            String s1 = scanner.next();
            String s2 = scanner.next();
            String s3 = scanner.next();
            int[][][] dp = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
            for (int[][] d : dp) for (int[] dd : d) Arrays.fill(dd, -1);
            System.out.println(solve(dp, s1, s2, s3, s1.length(), s2.length(), s3.length()));
        }
    }

    public static int solve(int[][][] dp, String s1, String s2, String s3, int i, int j, int k) {
        if (i == 0 && j == 0 && k == 0) return 0;
        if (i <= 0 || j <= 0 || k <= 0) return 0;
        if (dp[i][j][k] != -1) return dp[i][j][k];
        if (s1.charAt(i - 1) == s2.charAt(j - 1) && s2.charAt(j - 1) == s3.charAt(k - 1)) {
            return dp[i][j][k] = 1 + solve(dp, s1, s2, s3, i - 1, j - 1, k - 1);
        }
        return dp[i][j][k] = Math.max(solve(dp, s1, s2, s3, i - 1, j, k), Math.max(solve(dp, s1, s2, s3, i, j - 1, k), solve(dp, s1, s2, s3, i, j, k - 1)));
    }
}
