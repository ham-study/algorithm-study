/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/2206
 풀이시간 : 1h
 시간복잡도 : O(n)
 공간복잡도 : O(n)

 *******************************************************************************/

import java.io.*;
import java.util.*;

class Node {
    final int y, x;
    final int distance;
    final int bokenCnt;

    Node(int y, int x, int distance, int bokenCnt) {
        this.y = y;
        this.x = x;
        this.distance = distance;
        this.bokenCnt = bokenCnt;
    }
}

public class Main {
    private static int n, m;
    private static String[] map;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();

        if (n == 1 && m == 1 && map[0].charAt(0) == '1') {
            System.out.println(-1);
            return;
        }

        System.out.print(solve());
    }

    private static int solve() {
        Queue<Node> Q = new LinkedList<>();
        boolean[][][] visited = new boolean[2][n][m];

        Q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!Q.isEmpty()) {
            var cur = Q.poll();

            if (cur.y == n - 1 && cur.x == m - 1) return cur.distance;

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visited[cur.bokenCnt][ny][nx]) continue;

                if (map[ny].charAt(nx) == '0') {
                    Q.add(new Node(ny, nx, cur.distance + 1, cur.bokenCnt));
                    visited[cur.bokenCnt][ny][nx] = true;
                } else if (map[ny].charAt(nx) == '1' && cur.bokenCnt == 0) {
                    Q.add(new Node(ny, nx, cur.distance + 1, 1));
                    visited[cur.bokenCnt][ny][nx] = true;
                }
            }
        }

        return -1;
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new String[n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine();
        }

        br.close();
    }

}
