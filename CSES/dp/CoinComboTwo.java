
import java.util.Arrays;
import java.util.Scanner;

public class CoinComboTwo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[k+1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        for(int c: arr){
        for(int i=1;i<=k;i++){
                if(i>=c)dp[i] = (dp[i]+dp[i-c])%1000000007;
            }
        }
        System.out.println(dp[k]%1000000007);
    }
}
