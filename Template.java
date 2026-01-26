import java.io.*;
import java.util.*;

public class Template {

    // ---------- SETTINGS ----------
    static final boolean MULTI_TEST = true;   // set false if single test
    static final boolean DEBUG = false;       // set true for local debug prints

    // ---------- FAST INPUT ----------
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return -1;
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return -1L;
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        String next() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return null;
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }

    // ---------- GLOBALS ----------
    static FastScanner fs;
    static PrintWriter out;

    // ---------- SOLVE PER TEST ----------
    private static void solve() throws Exception {
        int n = fs.nextInt();
        char[] arr;
        arr = Objects.requireNonNull(fs.next()).toCharArray();
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            if (arr[l] == arr[r]) {
                l++;
                r--;
            } else {
                break;
            }
        }
        int ans = r - l + 1;
        out.println(Math.max(ans, 0));
        for (int i = l; i <= r; i++) {
            out.print(i + 1 + " ");
        }
        out.println();
    }


    // ---------- MAIN ----------
    public static void main(String[] args) throws Exception {
        fs = new FastScanner(System.in);
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        if (MULTI_TEST) {
            int t = fs.nextInt();
            while (t-- > 0) {
                solve();
            }
        } else {
            solve();
        }

        out.flush();
    }

    // ---------- UTILS (OPTIONAL) ----------

    static void debug(Object... o) {
        if (!DEBUG) return;
        System.err.println(Arrays.deepToString(o));
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long modPow(long a, long e, long mod) {
        long res = 1 % mod;
        a %= mod;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            e >>= 1;
        }
        return res;
    }

    static void sort(int[] a) {
        // sort + shuffle to avoid worst-case on sorted arrays
        Random rnd = new Random();
        for (int i = 0; i < a.length; i++) {
            int j = rnd.nextInt(a.length);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        Arrays.sort(a);
    }

    static void sort(long[] a) {
        Random rnd = new Random();
        for (int i = 0; i < a.length; i++) {
            int j = rnd.nextInt(a.length);
            long tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        Arrays.sort(a);
    }

    // DSU left here if you want it later, but not used now.
    static class DSU {
        int[] parent, rank;
        int components;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            components = n;
            for (int i = 1; i <= n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[ry] < rank[rx]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
            components--;
        }
    }

}
