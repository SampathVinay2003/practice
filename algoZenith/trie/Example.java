package algoZenith.trie;

import java.util.*;

 class Example {
         public static int minSwaps(int[] nums) {
             int n = nums.length;

             // Pair of (value, original index)
             int[][] arr = new int[n][2];
             for (int i = 0; i < n; i++) {
                 arr[i][0] = nums[i];
                 arr[i][1] = i;
             }

             // Sort by value
             Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

             boolean[] visited = new boolean[n];
             int ans = 0;

             for (int i = 0; i < n; i++) {
                 // already in correct place or already counted
                 if (visited[i] || arr[i][1] == i) continue;

                 int cycleSize = 0;
                 int j = i;

                 while (!visited[j]) {
                     visited[j] = true;
                     j = arr[j][1];
                     cycleSize++;
                 }

                 if (cycleSize > 1) {
                     ans += (cycleSize - 1);
                 }
             }
             return ans;
         }


     private static void swap(char[] a, int i, int j) {
        char t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // Quick test
    public static void main(String[] args) {
        System.out.println(minSwaps(new int[]{3,2, 1, 5, 4})); // expected 2
    }
}
