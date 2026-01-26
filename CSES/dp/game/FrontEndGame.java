
import java.util.Arrays;
import java.util.Scanner;

public class FrontEndGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[][][] dp = new int[2][n][n];
        for (int[][] d : dp) for (int[] dq : d) Arrays.fill(dq, -1);
        int sol = getRes(n, 0, 0, 0, n - 1, arr, 1, dp);
        System.out.println(sol);
    }

    public static int getRes(int n, int aScore, int bScore, int l, int r, int[] a, int aTurn, int[][][] dp) {
        if (r == l) {
            if (aTurn == 1) {
                return aScore + a[l];
            } else {
                return bScore + a[l];
            }
        }
        if (dp[aTurn][l][r] != -1) {
            return dp[aTurn][l][r];
        }
        if (aTurn == 1) {
            int ans = Math.max(getRes(n, aScore + a[l], bScore, l + 1, r, a, 0, dp) , getRes(n, aScore + a[r], bScore, l, r - 1, a, 0, dp));
            return dp[aTurn][l][r] = ans;
        } else {
            int ans = Math.max(getRes(n, aScore, bScore + a[l], l + 1, r, a, 1, dp) , getRes(n, aScore, bScore + a[r], l, r - 1, a, 1, dp));
            return dp[aTurn][l][r] = ans;
        }
    }
}