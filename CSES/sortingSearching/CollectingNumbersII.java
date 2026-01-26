
import java.io.*;
import java.util.*;

public class CollectingNumbersII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int q = Integer.parseInt(first[1]);

        int[] arr = new int[n + 1];  // 1-based: position -> value
        int[] pos = new int[n + 1];  // 1-based: value -> position

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            pos[arr[i]] = i;
        }

        // initial rounds = 1 + count of breaks pos[i] > pos[i+1]
        int rounds = 1;
        for (int i = 1; i < n; i++) if (pos[i] > pos[i + 1]) rounds++;

        StringBuilder out = new StringBuilder();

        while (q-- > 0) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            if (a == b) { // no-op
                out.append(rounds).append('\n');
                continue;
            }

            int x = arr[a];
            int y = arr[b];

            // collect affected i’s (i in [1..n-1])
            HashSet<Integer> affected = new HashSet<>();
            addIfValid(affected, x - 1, n);
            addIfValid(affected, x, n);
            addIfValid(affected, y - 1, n);
            addIfValid(affected, y, n);

            // subtract old contributions
            int before = 0;
            for (int i : affected) {
                if (pos[i] > pos[i + 1]) before++;
            }

            // perform the swap: update pos[] and arr[]
            int posX = pos[x], posY = pos[y];
            pos[x] = posY; pos[y] = posX;
            int tmp = arr[a]; arr[a] = arr[b]; arr[b] = tmp;

            // add new contributions
            int after = 0;
            for (int i : affected) {
                if (pos[i] > pos[i + 1]) after++;
            }

            rounds += (after - before);
            out.append(rounds).append('\n');
        }

        System.out.print(out.toString());
    }

    private static void addIfValid(HashSet<Integer> set, int i, int n) {
        if (1 <= i && i < n) set.add(i);
    }
}
