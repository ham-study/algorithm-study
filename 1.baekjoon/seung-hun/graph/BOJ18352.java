import java.io.*;
import java.util.*;
public class BOJ18352 {
    private static final int INF = 100000001;
    private static int N, M, K, X;
    private static Map<Integer, List<Integer>> graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new HashMap<>();
		
		for (int i=0;i<M;i++) {
		    st = new StringTokenizer(br.readLine());
		    int from = Integer.parseInt(st.nextToken());
		    int to = Integer.parseInt(st.nextToken());
		    
		    graph.putIfAbsent(from, new ArrayList<>());
		    graph.get(from).add(to);
		}
		
		System.out.println(solve());
	}
	
	private static String solve() {
	    List<Integer> result = dijkstra();
	    StringBuilder sb = new StringBuilder();
	    
	    if (result.isEmpty()) {
	        sb.append(-1);
	    } else {
	        for (int num : result) {
	            sb.append(num).append("\n");
	        }
	    }
	    
	    return sb.toString();
	}
	
	private static List<Integer> dijkstra() {
	    int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);
        dp[X] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> Integer.compare(a1[1], a2[1]));
        pq.add(new int[] {X, 0});
        
        while (!pq.isEmpty()) {
            int node = pq.peek()[0];
            int weight = pq.poll()[1];
            
            if (weight > dp[node]) continue;
            
            for (int adj : graph.getOrDefault(node, new ArrayList<>())) {
                int alt = weight + 1;
                
                if (dp[adj] > alt) {
                    dp[adj] = alt;
                    pq.add(new int[] {adj, alt});
                }
            }
        }
        
        return findKCity(dp);
	}
	
	private static List<Integer> findKCity(int[] dp) {
	    List<Integer> result = new ArrayList<>();
	    for (int i=1;i<=N;i++) {
	        if (dp[i] == K) {
	            result.add(i);
	        }
	    }
	    
	    return result;
	}
}
