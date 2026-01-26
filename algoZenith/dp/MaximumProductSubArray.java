package algoZenith.dp;

import java.util.Scanner;

public class MaximumProductSubArray {
    static long ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            ans = 0;
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(solve(arr));
        }
    }
    public static long solve(int[] arr){
        int n = arr.length;
        long left = 1;
        long right = 1;
        ans = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if (left == 0){
                left = 1;
            }
            if(right == 0){
                right = 1;
            }
            left *= arr[i];
            right *= arr[n-i-1];
            ans = Math.max(ans, Math.max(left, right));
        }
        return ans;
    }
}
