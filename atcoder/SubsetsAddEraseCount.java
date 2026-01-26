import java.io.*;
import java.util.*;

public class SubsetsAddEraseCount {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        long[] dp = new long[S + 1];
        dp[0] = 1; // empty subset

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);
            int x = Integer.parseInt(st.nextToken());

            if (op == '+') {
                for (int s = S; s >= x; --s) dp[s] = (dp[s]+dp[s - x])%998244353;
            } else { // erase one copy of x
                for (int s = x; s <= S; ++s) dp[s] = (dp[s]-dp[s - x])%998244353;
            }

            System.out.println(dp[S]%998244353);
        }
    }
}
