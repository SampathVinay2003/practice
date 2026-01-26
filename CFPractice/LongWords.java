package CFPractice;

import java.util.Scanner;


public class LongWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the newline after reading n

        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            int size = word.length();
            if (size > 10) {
                System.out.println(word.charAt(0) + String.valueOf(size - 2) + word.charAt(size - 1));
            } else {
                System.out.println(word);
            }
        }
        scanner.close();
    }
}