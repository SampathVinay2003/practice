
import java.util.Scanner;

public class ArrayDesc {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        long [][] dp = new long[n][m+1];
        if(arr[0] == 0){
            for(int i=1;i<=m;i++){
                dp[0][i] = 1;
            }
        }else{
            dp[0][arr[0]] = 1;
        }
        for(int i = 1; i < n; i++){
            if(arr[i] == 0){
                for(int j=1;j<=m;j++){
                    long s = dp[i-1][j];
                    if(j<m) s += dp[i-1][j+1];
                    if(j>1) s += dp[i-1][j-1];
                    s = s% 1000000007;
                    dp[i][j] = s;
                }
            } else{
              int x = arr[i];
              long s = dp[i-1][x];
              if(x<m) s += dp[i-1][x+1];
              if(x>1) s += dp[i-1][x-1];
              s = s% 1000000007;
              dp[i][x] = s;
            }
        }
        long ans = 0;
        for(int i=1;i<=m;i++){
            ans+= dp[n-1][i];
        }
        System.out.println(ans%1000000007);
    }
}