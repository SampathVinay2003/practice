
import java.util.Arrays;
import java.util.Scanner;

public class StickGame {
    public static void main(String[] args){
        Scanner scanner = new  Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[k];
        for(int i=0;i<k;i++){
            a[i] = scanner.nextInt();
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        for(int i=1;i<=n;i++){
            System.out.print(rec(i, a, dp)?"W":"L");
        }
    }
    private static boolean rec(int n, int[] a, int[] dp){
        if(n == 0)return false;
        if(dp[n] !=-1)return dp[n] == 1;
        for(int i=0;i<a.length;i++){
            if(n-a[i]>=0 && !rec(n-a[i], a, dp)){
                dp[n] = 1;
                return true;
            }
        }
        dp[n] = 0;
        return false;
    }
}
