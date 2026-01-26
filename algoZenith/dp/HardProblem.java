package algoZenith.dp;

import java.util.Scanner;

public class HardProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            scanner.nextLine();
            char[] chars = scanner.next().toCharArray();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            long[][] dp = new long[5][n];
            System.out.println(solve(chars, a, 0, 0, dp));
        }
    }

    public static long solve(char[] chars, int[] a, int match, int ind, long[][] dp) {
        if(match == 4)return Integer.MAX_VALUE/2;
        if(ind == chars.length) return 0;
        if(dp[match][ind] != 0) return dp[match][ind];
        long ans = a[ind] + solve(chars, a, match, ind+1, dp);//drop this char.
        char ch = chars[ind];
        if(match == 0){
            if(ch == 'h') ans = Math.min(ans, solve(chars, a, match+1, ind+1, dp));
            else ans = Math.min(ans, solve(chars, a, match, ind+1, dp));
        }
        if(match == 1){
            if(ch == 'a') ans = Math.min(ans, solve(chars, a, match+1, ind+1, dp));
            else ans = Math.min(ans, solve(chars, a, match, ind+1, dp));
        }
        if(match == 2){
            if(ch == 'r')ans = Math.min(ans, solve(chars, a, match+1, ind+1, dp));
            else  ans = Math.min(ans, solve(chars, a, match, ind+1, dp));
        }
        if (match == 3) {
            if (ch == 'd') ans = Math.min(ans, solve(chars, a, match + 1, ind + 1, dp));
            else ans = Math.min(ans, solve(chars, a, match, ind+1, dp));
        }
        return dp[match][ind] = ans;
    }
}
