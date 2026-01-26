import java.util.*;

public class Gemstones {
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            dp = new int[n][n];
            for (int[] row : dp) Arrays.fill(row, -1);
            System.out.println(solve(a, 0, n - 1));
        }
    }

    // minimum turns to remove a[l..r]
    static int solve(int[] a, int l, int r) {
        if (l > r) return 0;        // empty segment
        if (l == r) return 1;       // single element
        if( l+1 == r && a[r]==a[l])return 1;
        if (dp[l][r] != -1) return dp[l][r];

        // remove a[l] alone first
        int ans = 100000007;
        if(a[l]==a[r]){
            ans = Math.min(ans, solve(a, l + 1, r-1));
        }
        // try to pair a[l] with some k >= l+1 where a[l] == a[k]
        for (int k = l+1; k < r; k++) {

                int mid = solve(a, l , k );
                int right = solve(a, k+1, r);   // <-- k, not k+1
                ans = Math.min(ans, mid + right);

        }

        return dp[l][r] = ans;
    }
}
