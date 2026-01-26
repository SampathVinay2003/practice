package vivekGupta.form1DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class onlineQueriesSubsetsPrintForSum {
    static int[][] dp = new int[100][100];
    static int[] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int[] r : dp) Arrays.fill(r, -1);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //int sum = Integer.parseInt(br.readLine());
        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if(setDP(0, x)==1){
                print(x, 0);
            }else{
                System.out.println("Impossible");
            }
        }
    }

    // The reason why we do not use the sum till level-1 is that we need
    // to calculate the dp every time then it will cost us more time.
    // Instead, we try to calculate the leftover sum and
    // then calculate the dp on that from level to n-1 (whatever the final state is).
    public static int setDP(int level, int leftOverSum) {
        if (leftOverSum < 0) return 0;
        if (level == arr.length) return leftOverSum == 0 ? 1 : 0;
        if (dp[level][leftOverSum] != -1) return dp[level][leftOverSum];
        int ans = 0;
        if (setDP(level + 1, leftOverSum) == 1) {
            ans = 1;
        } else if (setDP(level + 1, leftOverSum - arr[level]) == 1) {
            ans = 1;
        }
        return dp[level][leftOverSum] = ans;
    }

    public static void print(int sum, int level) {
        if (level == arr.length) return;
        if (sum == 0) return;
        if (setDP(level + 1, sum) == 1) {
            print(sum, level + 1);
        } else if (setDP(level + 1, sum - arr[level]) == 1) {
            System.out.print(arr[level] + " ");// printing this here because if we don't consider the contents of this level then we will not be able to make the sum.
            print(sum - arr[level], level + 1);
        }
    }
}
