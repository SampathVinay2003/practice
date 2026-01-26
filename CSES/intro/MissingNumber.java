
import java.util.Scanner;

public class MissingNumber {
    public  static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long ans = (n*(n+1))/2;
        for (int i = 0; i < n-1; i++) {
            ans -= sc.nextLong();
        }
        System.out.println(ans);
    }
}
