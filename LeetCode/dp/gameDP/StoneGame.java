package LeetCode.dp.gameDP;

public class StoneGame {
    public static void main(String[] args) {
        int[] a = {4, 1, 7, 3, 8, 4, 7, 4, 5, 10};
        System.out.println(solve(a));
    }

    public static boolean solve(int[] a) {
        int n = a.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + a[i - 1];
        }
        return getRes(n, 0, 0, 0, n - 1, a, true);
    }

    public static boolean getRes(int n, int aScore, int bScore, int l, int r, int[] a, boolean aTurn) {
        if (r == l) {
            if (aTurn) {
                return aScore + a[l] > bScore;
            } else {
                return bScore + a[l] > aScore;
            }
        }
        if (aTurn) {
            return getRes(n, aScore + a[l], bScore, l + 1, r, a, false)
                    || getRes(n, aScore + a[r], bScore, l, r - 1, a, false);
        } else {
            return getRes(n, aScore, bScore + a[l], l + 1, r, a, true)
                    || getRes(n, aScore, bScore + a[r], l, r - 1, a, true);
        }
    }
}
