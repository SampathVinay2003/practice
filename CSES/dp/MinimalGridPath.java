import java.util.*;

public class MinimalGridPath {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) grid[i] = sc.next().toCharArray();

        // Frontier of positions that share the same minimal prefix so far
        // Encode (i,j) as long: (i<<32)|j to store in a set
        HashSet<Long> cur = new HashSet<>();
        cur.add(encode(0, 0));

        StringBuilder ans = new StringBuilder();
        ans.append(grid[0][0]);

        // We need exactly (2n-2) more characters to reach (n-1,n-1)
        for (int step = 1; step < 2 * n - 1; step++) {
            char best = '{'; // one past 'z'
            HashSet<Long> next = new HashSet<>();

            for (long p : cur) {
                int i = hi(p), j = lo(p);

                if (i + 1 < n) {
                    char c = grid[i + 1][j];
                    if (c < best) { best = c; next.clear(); }
                    if (c == best) next.add(encode(i + 1, j));
                }
                if (j + 1 < n) {
                    char c = grid[i][j + 1];
                    if (c < best) { best = c; next.clear(); }
                    if (c == best) next.add(encode(i, j + 1));
                }
            }

            ans.append(best);
            cur = next;
        }

        System.out.println(ans.toString());
    }

    static long encode(int i, int j) { return ((long)i << 32) | (j & 0xffffffffL); }
    static int hi(long p) { return (int)(p >> 32); }
    static int lo(long p) { return (int)p; }
}
