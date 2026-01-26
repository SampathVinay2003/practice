
import java.util.Arrays;
import java.util.Scanner;

public class MinimizingCoins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        dp[0] = 0;
        for(int c: arr){
            for(int i=0;i<=k;i++){
                if(i>=c)dp[i] = Math.min(dp[i], dp[i-c]+1);
            }
        }
        System.out.println(dp[k]>=Integer.MAX_VALUE/2?-1:dp[k]);
    }
}
