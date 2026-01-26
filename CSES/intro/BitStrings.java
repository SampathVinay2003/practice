
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BitStrings {
    public  static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = sc.nextInt();
        long[] dp = new long[10000000];
        dp[0]=1;
        for(int i=1;i<10000000;i++){
            dp[i] = (2*dp[i-1])%1000000007;
        }
        System.out.println(dp[n]%1000000007);
    }
}
