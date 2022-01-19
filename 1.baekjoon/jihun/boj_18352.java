/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/18352
 풀이시간 : 30m
 시간복잡도 : O(n + m + nlogn) = O() = O(300,000 + 1,000,000 + 300,000 * 2.5) = O(1,900,000)
 공간복잡도 : O(n)

*******************************************************************************/

import java.util.*;
import java.io.*;

public class Main {
    private static int n, m, k, x;
    private static LinkedList<Integer>[] adjList;

    public static void main(String[] args) throws Exception {
        init();

        solve();
    }

    private static void solve() { // bfs depth로 계산
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        Q.add(x);
        visited[x] = true;

        int depth = 0;

        while (!Q.isEmpty()) {
            int size = Q.size();

            while (size-- > 0) {
                int cur = Q.poll();

                for (var adj : adjList[cur]) {
                    if (visited[adj]) continue;

                    Q.add(adj);
                    visited[adj] = true;
                }
            }

            depth++;

            if (depth == k) {
                printResult(Q);
                return;
            }
        }

        printResult(Q); // Q가 비어있을 경우임 (-1일 출력될 것)

    }

    private static void printResult(Queue<Integer> results) {
        if (results.isEmpty()) {
            System.out.print(-1);
            return;
        }

        Collections.sort((List) results); // O(nlogn) = O(300,000 log300,000) = O(300,000 * 2.5)

        for (var result : results) {
            System.out.println(result);
        }
    }

    private static void init() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        adjList = new LinkedList[n + 1];

        for (int node = 1; node <= n; node++) {
            adjList[node] = new LinkedList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
        }

        br.close();
    }

}
