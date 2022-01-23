/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/1504
 풀이시간 : 1h
 시간복잡도 : O(mlogn) : 각 정점마다 인접한 간선들을 탐색하는 과정(O(m)) + 우선순위 큐에 인접정보를 넣고 빼는 과정(O(nlogn))
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int v, cost;

    Node(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node node) {
        return this.cost - node.cost; // cost 오름차순
    }
}

public class Main {
    private static int n, m;
    private static int v1, v2;
    private static LinkedList<Node>[] adjList;

    public static void main(String[] args) throws Exception {
        init();

        int costA = solve(v1, v2);
        int costB = solve(v2, v1);

        if (costA == -1) {
            System.out.print(costB);
            return;
        }

        if (costB == -1) {
            System.out.print(costA);
            return;
        }

        System.out.print(Math.min(costA, costB));
    }

    private static int solve(int v1, int v2) {
        int cost1 = dijkstra(1, v1);
        if (cost1 == -1) return -1;

        int cost2 = dijkstra(v1, v2);
        if (cost2 == -1) return -1;

        int cost3 = dijkstra(v2, n);
        if (cost3 == -1) return -1;

        return cost1 + cost2 + cost3;
    }

    private static int dijkstra(int from, int to) {
        var pq = new PriorityQueue<Node>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[from] = 0;
        pq.add(new Node(from, dist[from]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.v == to) break;

            for (Node adj : adjList[cur.v]) {
                if (dist[adj.v] >= dist[cur.v] + adj.cost) {
                    dist[adj.v] = dist[cur.v] + adj.cost;
                    pq.add(new Node(adj.v, dist[adj.v]));
                }
            }
        }

        return dist[to] == Integer.MAX_VALUE ? -1 : dist[to];
    }

    private static void init() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new LinkedList<Node>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cost));
            adjList[to].add(new Node(from, cost));
        }

        st = new StringTokenizer(br.readLine());

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        br.close();
    }

}
