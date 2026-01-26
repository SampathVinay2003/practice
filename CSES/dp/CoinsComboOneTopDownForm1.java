package CSES.dp;

import java.util.Arrays;
import java.util.Scanner;

public class CoinsComboOneTopDownForm1 {
    static int[]dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int amount = scanner.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        System.out.println(solve(dp, coins, n, amount));
    }

    public static int solve(int[] dp, int[] coins, int n, int amount) {
        //pruning
        if (amount < 0) return 0;
        //base
        if (amount == 0) return 1;

        if (dp[amount] != -1) return dp[amount];
        int ans = 0;
        for (int c : coins) {
            ans += solve(dp, coins, n, amount - c);
        }
        return dp[amount] = ans;
    }
}
