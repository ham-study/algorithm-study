/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/21278
 풀이시간 : 30m - 부분성공
 시간복잡도 : O(nC2 * 100^2) ~ O(5천만)
 공간복잡도 : O(n)

 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<Integer>[] adjList;

    private static int v1 = 100, v2 = 100, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        solve();

        System.out.println(v1 + " " + v2 + " " + min);
    }

    private static void solve() {
        // 100 c 2 콤비네이션
        combination(new boolean[n + 1], 0);
    }

    private static void combination(boolean[] selected, int size) {
        if (size == 2) {
            var result = new ArrayList<Integer>(2);
            for (int i = 1; i <= n; i++) {
                if (!selected[i]) continue;

                result.add(i);
            }

            bfs(result.get(0), result.get(1));

            // System.out.println("v1: " + result.get(0) + " v2: " + result.get(1));

            return;
        }

        for (int i = 1; i <= n; i++) {
            if (selected[i]) continue;

            selected[i] = true;
            combination(selected, size + 1);
            selected[i] = false;
        }
    }

    private static void bfs(int startv1, int startv2) {
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        Q.add(startv1);
        Q.add(startv2);
        visited[startv1] = visited[startv2] = true;

        int depth = 1;
        int aroundSum = 0;

        while (!Q.isEmpty()) {
            int size = Q.size();

            while (size-- > 0) {
                int cur = Q.poll();

                for (var adj : adjList[cur]) {
                    if (visited[adj]) continue;

                    Q.add(adj);
                    visited[adj] = true;

                    aroundSum += (depth * 2);
                }
            }

            depth++;
        }

        if (min > aroundSum) {
            min = aroundSum;

            if (Math.min(v1, v2) > Math.min(startv1, startv2)) {
                v1 = startv1;
                v2 = startv2;
            } else if (Math.min(v1, v2) == Math.min(startv1, startv2)) {
                int tmpMax1 = Math.max(v1, v2);
                int tmpMax2 = Math.max(startv1, startv2);

                if (tmpMax1 > tmpMax2) {
                    v1 = startv1;
                    v2 = startv2;
                }
            }
        }

    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int v11 = Integer.parseInt(st.nextToken());
            int v22 = Integer.parseInt(st.nextToken());

            adjList[v11].add(v22);
            adjList[v22].add(v11);
        }

        br.close();
    }

}
