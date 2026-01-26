
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Knight {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (long k = 1; k <= n; k++) {
            long total = k*k*(k*k - 1)/2;
            long attack = 4*(k-1)*(k-2);
            sb.append(total - attack).append('\n');
        }
        System.out.print(sb.toString());
    }

}

