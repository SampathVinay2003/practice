package CFPractice;

import java.util.Scanner;

public class Watermelon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        if(size <=2 || size%2 == 1){
            System.out.println("NO");
        }else{
            System.out.println("YES");
        }
    }
}
