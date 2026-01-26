package CFPractice;

import java.util.Scanner;

public class ReverseXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  // number of test cases

        while (t-- > 0) {
            int num = sc.nextInt();
            String binary = Integer.toBinaryString(num);

            // Remove trailing zeros
            binary = binary.replaceAll("0+$", "");

            // Check if palindrome
            String reversed = new StringBuilder(binary).reverse().toString();
            boolean isPalindrome = binary.equals(reversed);

            // Count number of 1s
            long countOnes = binary.chars().filter(ch -> ch == '1').count();

            // Print result
            if (isPalindrome && countOnes % 2 == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }

        sc.close();
    }
}
