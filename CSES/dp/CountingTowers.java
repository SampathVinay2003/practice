import java.util.Scanner;

public class CountingTowers {
    static final int MAXN = 1_000_007;
    static final int MOD = 1_000_000_007;
    static final long[][] dp = new long[MAXN][2];

    static {
        // Precompute once
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < MAXN; i++) {
            dp[i][0] = (4L * dp[i - 1][0] % MOD + dp[i - 1][1]) % MOD;
            dp[i][1] = (2L * dp[i - 1][1] % MOD + dp[i - 1][0]) % MOD;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            System.out.println((dp[n - 1][0] + dp[n - 1][1]) % MOD);
        }
        scanner.close();
    }
}
