/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/13549
 풀이시간 : 1h
 시간복잡도 : O(?)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int num, cost;

    Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost; // 오름차순
    }
}

public class Main {

    private static int n, k;
    private static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int result = solve();

        System.out.print(result);
    }

    private static int solve() {
        PriorityQueue<Node> Q = new PriorityQueue<>();
        Q.add(new Node(n, 0));
        var visited = new HashSet<Integer>();
        visited.add(n);

        while (!Q.isEmpty()) {
            var cur = Q.poll();

            visited.add(cur.num); /// visited 마킹이 여기에 있어야 같은 점으로 가는 최소값이 먼저 빼지게 됨

            if (cur.num == k) {
                return cur.cost;
            }

            if (cur.num * 2 <= MAX && !visited.contains(cur.num * 2)) {
                Q.add(new Node(cur.num * 2, cur.cost));
                //visited.add(cur.num * 2);
            }

            if (cur.num + 1 <= MAX && !visited.contains(cur.num + 1)) {
                Q.add(new Node(cur.num + 1, cur.cost + 1));
                //visited.add(cur.num + 1);
            }

            if (cur.num - 1 >= 0 && !visited.contains(cur.num - 1)) {
                Q.add(new Node(cur.num - 1, cur.cost + 1));
                //visited.add(cur.num - 1);
            }

        }

        return 0;
    }

}
