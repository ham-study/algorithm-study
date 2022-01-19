import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class BOJ1260 {
    private static int N, M, V;
    private static Map<Integer, List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new HashMap<>();

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
    
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (Integer key : graph.keySet()) {
            Collections.sort(graph.get(key));
        }
        
        boolean[] visited = new boolean[N + 1];
        visited[V] = true;
        dfs(V, visited);
        System.out.println();
        bfs();
    }

    private static void dfs(int node, boolean[] visited) {
        System.out.print(node + " ");
        for (int v : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited[v]) {
                visited[v] = true;
                dfs(v, visited);
            }
        }
    }

    private static void bfs() {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        
        q.add(V);
        visited[V] = true;

        while(!q.isEmpty()) {
            int u = q.poll();

            System.out.print(u + " ");

            for (int v : graph.getOrDefault(u, new ArrayList<>())) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }

    }
}
/**
 * 시간: 15분
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */