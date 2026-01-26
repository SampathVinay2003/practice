package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class SubSetQueries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        while (q-- > 0) {
            int x = sc.nextInt();
            boolean ans = solve(x, 0, arr, dp);
            System.out.println(ans ? 1 : -1);
        }
    }

    public static boolean solve(int sum, int ind, int[] arr, int[] dp) {
        if (sum == 0) return true;
        if (sum < 0 || ind == arr.length || sum >= dp.length) return false;
        if (dp[sum] != Integer.MIN_VALUE) {
            return dp[sum] == 1;
        }
        return solve(sum - arr[ind], ind + 1, arr, dp) || solve(sum, ind + 1, arr, dp);
    }
}
