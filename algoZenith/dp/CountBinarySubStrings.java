package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class CountBinarySubStrings {
    public static long MOD = 1_000_000_000 + 7;
    static long[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            dp = new long[n + 1][8];
            System.out.println(solve(n));
        }
    }

    //    public static long solve(int n) {
//        long x = 0;
//        if (n < 4) return (long) Math.pow(2, n);
//        if (n == 4) return 1;
//        else{
//            x = (long) Math.pow(2, n - 4)*(n-3);
//        }
//        return (long) Math.pow(2, n) - (x);
//    }
    public static long solve(int n) {
        String T = "0100";
        // 8 because match ∈ [0..7]
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1L);
        return getCount(0, n, 0) % MOD;
    }

    public static long getCount(int level, int n, int match) {
        //pruning
        //no pruning here because when we find 3 chars just like T
        // then we will not let it take the wrong one
        if (level == n) {
            return 1;
        }
        if (dp[level][match] != -1) {
            return dp[level][match];
        }
        if (level >= 3 && match == 2) {//010  -> we get wrong as 2 means its is considering 10 as well, so we need to add level ==3 as well
            return getCount(level + 1, n, 5);
            //now the match will be 5 as we take
            // 101 since we need count of substrings with
            // 101 so that we can get count of substrings with other than 0100.
        } else {
            return dp[level][match] = (getCount(level + 1, n, (match << 1) & 7) + getCount(level + 1, n, (match << 1 | 1) & 7)) % MOD;
        }
    }
}
