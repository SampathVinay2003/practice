import java.util.Scanner;

public class EditDist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        long[][] dp = new long[s1.length() + 1][s2.length() + 1];
        for(int i=0;i< s1.length();i++){
            dp[i][0] = i;
        }
        for(int i=0;i< s2.length();i++){
            dp[0][i] = i;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    //System.out.print(dp[i][j]+" ");
                } else {
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1])+1;
                    //System.out.print(dp[i][j]+" ");
                }
            }
            //System.out.println();
        }
        System.out.println(dp[s1.length()][s2.length()]);
        sc.close();
    }
}