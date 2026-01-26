package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class SQueen {
    public static int count;
    public static int[] queen;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        queen = new int[n];
        Arrays.fill(queen, -1);
        count = 0;
        solve(0, n);
        System.out.println(count);
    }

    public static boolean isPossible(int col, int row) {
        for (int i = 0; i < row; i++) {
            int pCol = queen[i];
            if (col == pCol || Math.abs(row - i) == Math.abs(col - pCol) || isKNightPowers(row, col)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isKNightPowers(int row, int col) {
        int[] x = {-2, -1, -1, -2};
        int[] y = {-1, -2, 2, 1};
        for (int i = 0; i < 4; i++) {
            int nrow = row + x[i];
            int ncol = col + y[i];
            if (nrow >= 0 && nrow < row && ncol >= 0 && ncol < queen.length && queen[nrow] == ncol) {
                return true;
            }
        }
        return false;
    }

    public static void solve(int row, int n) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isPossible(col, row)) {
                queen[row] = col;
                solve(row + 1, n);
                queen[row] = -1;
            }
        }
    }
}
