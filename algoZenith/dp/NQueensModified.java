package algoZenith.dp;

import java.util.Arrays;
import java.util.Scanner;

public class NQueensModified {
    static int[] queen;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = new char[8][8];
        for (int i = 0; i < 8; i++) {
            grid[i] = scanner.next().toCharArray();
        }
        queen = new int[8];
        Arrays.fill(queen, -1);
        System.out.println(solve(grid, 0));
    }

    public static long solve(char[][] grid, int n) {
        if (n == 8) return 1;
        int ans = 0;
        for (int col = 0; col < 8; col++) {
            if (grid[n][col] == '*') continue;
            if (isPossible(n, col)) {
                queen[n] = col;
                ans += solve(grid, n + 1);
                queen[n] = -1;
            }
        }
        return ans;
    }

    public static boolean isPossible(int row, int col) {
        for (int i = 0; i < row; i++) {
            int pCol = queen[i];
            if (col == pCol || Math.abs(row - i) == Math.abs(col - pCol)) {
                return false;
            }
        }
        return true;
    }
}
