
import java.util.Scanner;

public class Permutations {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        if(n == 1){
            System.out.println(1);
            return;
        }
        if(n<=3){
            System.out.println("NO SOLUTION");
            return;
        }
        if(n%2 == 0){
            long a = 2;
            while(a <= n){
                System.out.print(a+" ");
                a+= 2;
            }
            a = 1;
            while(a <= n-1) {
                System.out.print(a+" ");
                a += 2;
            }
        }else{
            long a = 2;
            while(a <= n-1){
                System.out.print(a+" ");
                a+= 2;
            }
            a = 1;
            while(a <= n) {
                System.out.print(a+" ");
                a += 2;
            }
        }
    }
}
