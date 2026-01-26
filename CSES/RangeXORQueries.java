import java.io.*;
import java.util.*;

public class RangeXORQueries {
    // Fast scanner
    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do { c = read(); } while (c <= ' ');      // skip ws
            if (c == '-') { s = -1; c = read(); }
            while (c > ' ') { x = x * 10 + (c - '0'); c = read(); }
            return x * s;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int k = fs.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = fs.nextInt();

        // prefixXOR[i] = XOR of arr[0..i-1]
        int[] prefixXOR = new int[n + 1];
        for (int i = 0; i < n; i++) prefixXOR[i + 1] = prefixXOR[i] ^ arr[i];

        StringBuilder out = new StringBuilder(k * 3); // rough capacity
        for (int i = 0; i < k; i++) {
            int l = fs.nextInt();
            int r = fs.nextInt();

            // If your queries are 1-based inclusive [l, r]:
            int ans = prefixXOR[r] ^ prefixXOR[l - 1];

            // If they are 0-based inclusive, use instead:
            // int ans = prefixXOR[r + 1] ^ prefixXOR[l];

            out.append(ans).append('\n');
        }
        System.out.print(out.toString());
    }
}
