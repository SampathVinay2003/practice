package CSES.intro;

import java.util.Scanner;

public class WeirdAlgo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        while(n != 1){
            System.out.print(n+" ");
            if(n%2 == 0){
                n /= 2;
            }else{
                n *= 3;
                n += 1;
            }
        }
        System.out.println(n);
    }
}
