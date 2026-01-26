
import java.util.Scanner;

public class BookShop {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxPrice = sc.nextInt();
        int[] price = new int[n];
        for(int i=0;i<n;i++){
            price[i] = sc.nextInt();
        }
        int[] pages = new int[n];
        for(int i=0;i<n;i++) {
            pages[i] = sc.nextInt();
        }
        int[] dp = new int[maxPrice+1];
        for(int i=0;i<n;i++){
            for(int j=maxPrice;j>=price[i];j--){
                dp[j] = Math.max(dp[j], dp[j-price[i]]+pages[i]);
            }
        }
        System.out.println(dp[maxPrice]);
    }
}
