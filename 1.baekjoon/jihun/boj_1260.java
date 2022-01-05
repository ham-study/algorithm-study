/**
 * https://www.acmicpc.net/problem/1260
 */

import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int startV;
    private static boolean[][] map; // 인접행렬

    public static void main(String[] args) throws IOException {
        init();

        doDfs();
        doBfs();
    }

    private static void doBfs() {
        var sb = new StringBuilder();
        var isVidited = new boolean[N + 1];
        Queue<Integer> Q = new LinkedList<>();

        Q.add(startV);
        isVidited[startV] = true;
        sb.append(startV).append(" ");

        while (!Q.isEmpty()) {
            int curV = Q.poll();

            for (int v = 1; v <= N; v++) {
                if (isVidited[v] || !map[curV][v] || curV == v) continue;

                Q.add(v);
                isVidited[v] = true;
                sb.append(v).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    private static void doDfs() {
        var sb = new StringBuilder();
        var isVidited = new boolean[N + 1];

        isVidited[startV] = true;
        dfs(startV, isVidited, sb.append(startV).append(" "));
        System.out.println(sb.toString());
    }

    private static void dfs(int curV, boolean[] vidited, StringBuilder sb) {
        vidited[curV] = true;

        for (int v = 1; v <= N; v++) {
            if (vidited[v] || !map[curV][v] || curV == v) continue;

            dfs(v, vidited, sb.append(v).append(" ")); // 연결된 모든 접점으로 가보기
        }
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startV = Integer.parseInt(st.nextToken());

        map = new boolean[N + 1][N + 1];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            map[v1][v2] = map[v2][v1] = true;
        }

        br.close();
    }

}
/**
 * 소요시간: 0.14초
 * 시간복잡도: O(N^2) : 매 단계마다 모든 접점에 대해 갈 수 있는지 검사하므로 n^2 (인접리스트로 할 경우 O(N+M))
 * 공간복잡도:
 */
