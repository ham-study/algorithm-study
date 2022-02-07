/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/2573
 풀이시간 : 30m
 시간복잡도 :
 공간복잡도 : O(n ^ n * 10) ~ O(900,000)

 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int ySize, xSize;
    private static int[][] map, mapCopy;

    private static int[] dy = new int[]{-1, 0, 1, 0};
    private static int[] dx = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();

        int year = solve();

        System.out.print(year);
    }

    private static int solve() {
        int year = 0;

        while (true) {
            // bfs로 area 개수 구하기
            var visited = new boolean[ySize][xSize];
            int areaNum = 0;

            for (int i = 1; i <= ySize - 2; i++) {
                for (int j = 1; j <= xSize - 2; j++) {
                    if (visited[i][j] || map[i][j] == 0) continue;

                    bfs(i, j, visited);
                    areaNum++;
                }
            }

            if (areaNum >= 2) return year;
            if (areaNum == 0) return 0;

            // 녹이기
            for (int i = 1; i <= ySize - 2; i++) {
                for (int j = 1; j <= xSize - 2; j++) {
                    if (map[i][j] == 0) continue;

                    int meltResult = map[i][j] - melt(i, j);
                    mapCopy[i][j] = meltResult < 0 ? 0 : meltResult;
                }
            }

            for (int i = 1; i <= ySize - 2; i++) {
                for (int j = 1; j <= xSize - 2; j++) {
                    map[i][j] = mapCopy[i][j];
                }
            }

            year++;
        }
    }

    private static int melt(int y, int x) {
        int zeroSize = 0;

        for (int i = 0; i < 4; i++) {
            int adjY = y + dy[i];
            int adjX = x + dx[i];

            if (map[adjY][adjX] == 0) zeroSize++;
        }

        return zeroSize;
    }

    private static void bfs(int y, int x, boolean[][] visited) {
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{y, x});
        visited[y][x] = true;

        while (!Q.isEmpty()) {
            var cur = Q.poll();
            int curY = cur[0];
            int curX = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];

                if (ny < 0 || nx < 0 || ny >= ySize || nx >= xSize) continue;
                if (map[ny][nx] == 0 || visited[ny][nx]) continue;

                Q.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        ySize = Integer.parseInt(st.nextToken());
        xSize = Integer.parseInt(st.nextToken());

        map = new int[ySize][xSize];

        for (int i = 0; i < ySize; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < xSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mapCopy = new int[ySize][xSize];
    }

}
