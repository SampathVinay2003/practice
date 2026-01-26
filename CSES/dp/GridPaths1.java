
import java.util.Scanner;

public class GridPaths1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] matrix = new char[n][n];
        for(int i=0;i<n;i++){
            String s = sc.next();
            for(int j=0;j<n;j++){
                matrix[i][j] = s.charAt(j);
            }
        }
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            if(matrix[i][0] == '.')dp[i][0] = 1;
            else break;
        }
        for(int i=0;i<n;i++){
            if(matrix[0][i] == '.')dp[0][i] = 1;
            else break;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j] == '.')dp[i][j] =( dp[i-1][j] + dp[i][j-1])%1000000007;
            }
        }
        System.out.println(dp[n-1][n-1]);
    }
}
