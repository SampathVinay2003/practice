package CFPractice;

import java.io.*;
import java.util.*;

public class Zuma {

    // ---------- SETTINGS ----------
    static final boolean MULTI_TEST = false;   // set false if single test
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
        // TODO: write solution for ONE test case here
        int n = fs.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nextInt();
        }
        long[][] dp = new long[n][n];
        for(long[] d: dp)Arrays.fill(d,-1L);
        out.println(solution(arr, 0, n-1, dp));
    }
    private static long solution(int[] arr, int l,int r, long[][] dp){
        if(l>r)return 0;
        if(l==r)return 1;
        if(l==r-1 && arr[l]==arr[l+1])return 1;
        if(dp[l][r] != -1)return  dp[l][r];
        long ans = 1 + solution(arr, l+1, r, dp);
        if(arr[l]==arr[r]){
            ans = Math.min(ans, solution(arr, l+1, r-1, dp));
        }
        for(int i=l+1;i<r;i++){
            long left = solution(arr, l, i, dp);
            long right = solution(arr, i+1, r, dp);
            ans = Math.min(ans, left+ right);
        }
        return dp[l][r] = ans;
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
}
