
import java.util.Scanner;

public class Repetitions {
    public  static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        long l = 0;
        long r = 0;
        long ans = 1;
        while (l<s.length()) {
            while(r<s.length() && s.charAt((int)l) == s.charAt((int)r)){
                r++;
            }
            ans = Math.max(ans, r-l);
            l = r;
        }
        System.out.println(ans);
    }
}
