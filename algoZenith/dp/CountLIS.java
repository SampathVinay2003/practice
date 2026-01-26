package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class CountLIS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(solve(arr));
        }
    }

    public static int solve(int[] arr) {
        int n = arr.length;
        long[] dp = new long[n];
        long[] count = new long[n];
        long maxLen = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];      // new best length
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] = (count[j]+count[i])%1000000007;     // one more way
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int result = 0;
        for(int i=0;i<n;i++){
            if(dp[i]==maxLen){
                result += count[i];
            }
        }
        return result;
    }
}
