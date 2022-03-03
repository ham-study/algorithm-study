// package 최단경로_G4_서강그라운드;

// import java.io.*;
// import java.util.*;

// public class Main2 {

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         st = new StringTokenizer(br.readLine());
//         int n = Integer.parseInt(st.nextToken());   // 정점 수
//         int m = Integer.parseInt(st.nextToken());   // 수색범위
//         int r = Integer.parseInt(st.nextToken());   // 간선 수

//         int[] items = new int[n+1];
//         st = new StringTokenizer(br.readLine());
//         for(int i=1; i<=n; i++){
//             items[i] = Integer.parseInt(st.nextToken());
//         }

//         int[][] edges = new int[r][3];
//         for(int i=0; i<r; i++){
//             st = new StringTokenizer(br.readLine());
//             edges[i][0] = Integer.parseInt(st.nextToken());
//             edges[i][1] = Integer.parseInt(st.nextToken());
//             edges[i][2] = Integer.parseInt(st.nextToken());
//         }

//         System.out.println(solution(m, items, edges));
//     }

//     public static class Node implements Comparable<Node> {
//         public int num;
//         public int weight;

//         public Node(int num, int weight){
//             this.num = num;
//             this.weight = weight;
//         }

//         @Override
//         public int compareTo(Node o){
//             return this.weight - o.weight;
//         }
//     }

//     public static final int INF = Integer.MAX_VALUE;


//     public static int solution(int m, int[] items, int[][] edges){

//         int n = items.length - 1;

//         HashMap<Integer, ArrayList<Node>> graph = new HashMap<>();
//         for(int i=1; i<=n; i++){
//             graph.put(i, new ArrayList<>());
//         }
//         for(int[] edge : edges){
//             graph.get(edge[0]).add(new Node(edge[1], edge[2]));
//             graph.get(edge[1]).add(new Node(edge[0], edge[2]));
//         }

//         int[][] dist = new int[n+1][n+1];
//         for(int i=1; i<=n; i++){
//             Arrays.fill(dist[i], INF);
//         }
        
//         // for(int i=1; i<=n; i++){
//         //     for(int j=1; j<=n; j++){
//         //         if(i==j){
//         //             dist[i][j] = 0;
//         //         }
//         //         else{
//         //             dist[i][j] = INF;
//         //         }
//         //     }
//         // }

//         for(int i=1; i<=n; i++){
//             dijkstra(i, n, graph, dist);
//         }

//         int max = 0;
//         for(int i=1; i<=n; i++){
//             int sum = 0;
//             for(int j=1; j<=n; j++){
//                 if(dist[i][j] <= m){
//                     sum += items[j];
//                 }
//             }
//             if(sum > max){
//                 max = sum;
//             }
//         }
        
//         return max;
//     }

//     public static void dijkstra(int start, int n, int m, HashMap<Integer, ArrayList<Node>> graph){
        
//         int[] dist = new int[n+1];
//         Arrays.fill(dist, INF);

//         boolean[] isVisited = new boolean[n+1];

//         PriorityQueue<Node> queue = new PriorityQueue<>();
//         queue.offer(new Node(start, 0));
//         isVisited[start] = true;
//         dist[start] = 0;

//         while(!queue.isEmpty()){
//             Node cur = queue.poll();
            
//             for(Node next : graph.get(cur.num)){
//                 int newDist = dist[cur.num] + next.weight;
//                 if(!isVisited[next.num] && newDist < dist[next.num]){
//                     isVisited[next.num] = true;
//                     dist[next.num] = newDist;
//                     queue.offer(next);
//                 }
//             }
//         }

//         for()


//     }
    
// }
