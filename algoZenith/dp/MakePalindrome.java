package algoZenith.dp;

import java.util.Scanner;

public class MakePalindrome {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-- > 0){
            String s = scanner.next();
            String rev = new StringBuilder(s).reverse().toString();
            int ans = lengthOfLCS(rev, s);
            if(ans == 0)ans++;
            System.out.println(s.length()-ans);
        }
    }
    public  static int lengthOfLCS(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return  dp[n][m];
    }
}
