
import java.util.Scanner;

public class Dice {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=6;j++){
                if(i-j>=0)dp[i] += dp[i-j];
            }
        }
        System.out.println(dp[n]);
    }
}
