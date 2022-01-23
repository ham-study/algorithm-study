import java.util.*;
import java.io.*;
public class BOJ1504 {
    
    private static final int INF = 99999999;
    private static int N, E;
    private static Map<Integer, List<int[]>> graph;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new HashMap<>();
        
        for (int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            addAdjacent(u, v, w);
            addAdjacent(v, u, w);
        }
        
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        
        int result1 = solve(v1, v2);
        int result2 = solve(v2, v1);
        
        if (result1 == -1) {
            System.out.println(result2);
        } else if (result2 == -1) {
            System.out.println(result1);
        } else {
            System.out.println(Math.min(result1, result2));
        }
    }
    
    private static int solve(int node1, int node2) {
        int dist1 = dijkstra(1, node1);
        if (dist1 == -1) {
            return -1;
        }
        
        int dist2 = dijkstra(node1, node2);
        if (dist2 == -1) {
            return -1;
        }
        
        int dist3 = dijkstra(node2, N);
        if (dist3 == -1) {
            return -1;
        }
        
        return dist1 + dist2 + dist3;
    }
    
    private static int dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> Integer.compare(a1[1], a2[1]));
        Arrays.fill(dist, INF);
        
        pq.add(new int[]{start, 0});
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            int node = pq.peek()[0];
            int weight = pq.poll()[1];
            
            if (weight > dist[node]) continue;

            for (int[] adj : graph.getOrDefault(node, new ArrayList<>())) {
                int alt = weight + adj[1];
                if (dist[adj[0]] > alt) {
                    dist[adj[0]] = alt;
                    pq.add(new int[] {adj[0], alt});
                }
            }
        }
        
        return dist[end] == INF ? -1 : dist[end];
    }
    
    private static void addAdjacent(int parent, int child, int weight) {
        graph.putIfAbsent(parent, new ArrayList<>());
        graph.get(parent).add(new int[] {child, weight});
    }
}
/**
 * 소요시간: 25분
 * 시간복잡도: O(ElogV)
 * 공간복잡도: O(E + N)
 */
/***
 * 1 ~ A ~ B ~ N 사이 최단경로?
 * 
 * dist[1][A] + dist[A][B] + dist[B][N]
 * dist[1][B] + dist[B][A] + dist[A][N]
 * 
 * O(ElogV) * 3
 * 
 * dijkstra(start, end):
 *  return dist[end];
 */
