package algoZenith.dp.gamedp;

import java.util.Arrays;
import java.util.Scanner;

public class Chips {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] dp = new int[n + 1];
            Arrays.fill(dp, -1);
            int ans = solve(dp, n);
            System.out.println(ans == 1 ? "Vivek" : "Abhishek");
        }
    }

    public static int solve(int[] dp, int n) {
        if(n == 0)return 0;
        if(n == 1)return  1;
        if(dp[n] != -1) return dp[n];
        for(long i=0;(1<<i)<=n;i++){
            if(solve(dp, n-(1<<i)) == 0){
                dp[n] = 1;
                return 1;
            }
        }
        return  dp[n] = 0;
    }
}
