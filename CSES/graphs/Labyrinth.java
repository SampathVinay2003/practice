import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Labyrinth {
    static FastReader sc = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    static int n, m;
    static char[][] grid;
    static int[][] from;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dir = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new char[n][m];
        from = new int[n][m];
        visited = new boolean[n][m];

        int sx = -1, sy = -1, ex = -1, ey = -1;

        for (int i = 0; i < n; i++) {
            grid[i] = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'A') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    ex = i;
                    ey = j;
                }
            }
        }
        StringBuilder path = new StringBuilder();
        if (bfs(sx, sy, ex, ey)) {
            out.println("YES");
            while (sx != ex || sy != ey) {
                int d = from[ex][ey];
                char di = dir[d];
                path.append(di);
                ex -= dx[d];
                ey -= dy[d];
            }
            out.println(path.length());
            out.println(path.reverse().toString());
        } else {
            out.println("NO");
        }

        out.flush();
    }

    static boolean bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        while (!q.isEmpty()) {
            int[] front = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = front[0] + dx[i], ny = front[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m &&
                        !visited[nx][ny] &&
                        (grid[nx][ny] == '.' || grid[nx][ny] == 'B')) {
                    visited[nx][ny] = true;
                    from[nx][ny] = i;
                    q.add(new int[]{nx, ny});
                    if (nx == ex && ny == ey) return true;
                }
            }
        }

        return false;
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}