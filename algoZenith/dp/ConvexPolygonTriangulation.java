package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class ConvexPolygonTriangulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            int count = 0;
            int ans = 0;
            Arrays.sort(arr);
            for (int i = 0; i < n; i++) {
                int j = i + 1;
                int k = j + 1;
                while (k < n) {
                    ans += arr[i] * arr[j] * arr[k];
                    count++;
                    k++;
                }
                if (count == n - 2) {
                    break;
                }
            }
            System.out.println(ans);
        }


    }
}
