import java.io.*;
import java.util.*;
public class BOJ21278 {
    private static final int INF = 100001;
    private static int[][] board;
    private static int[][] dist;
    private static int N, M;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        dist = new int[N + 1][N + 1];
        
        for (int[] d : dist) {
            Arrays.fill(d, INF);
        }
        
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            dist[u][v] = 1;
            dist[v][u] = 1;
        }
        
        calcDist();
        System.out.println(findChickenStore());
    }
    
    private static void calcDist() {
        for (int k=1;k<=N;k++) {
            for (int i=1;i<=N;i++) {
                for (int j=1;j<=N;j++) {
                    if (i == j) continue;
                    if (dist[i][j] == 0) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    } else {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }    
    }
    
    private static String findChickenStore() {
        int distance = INF;
        int[] locations = new int[2];
        for (int i=1;i<N;i++) {
            for (int j=i+1;j<=N;j++) {
                int result = getTotalDist(i, j);
                
                if (result < distance) {
                    locations[0] = i;
                    locations[1] = j;
                    distance = result;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        return sb.append(locations[0]).append(" ")
                 .append(locations[1]).append(" ")
                 .append(distance * 2)
                 .toString();
    }
    
    private static int getTotalDist(int node1, int node2) {
        int result = 0;
        for (int i=1;i<=N;i++) {
            if (i == node1 || i == node2) continue;
            result += Math.min(dist[node1][i], dist[node2][i]);
        }
        
        return result;
    }
 }
 
 /***
  * 1. 소요시간: 23분
  * 2. 시간복잡도: O(N^3)
  * 3. 공간복잡도: O(N^2)
  */
/***
 * 1. 모든 건물에서 모든 건물까지 거리 구하기 - 플로이드 와샬
 * 2. 두개 건물 선정해서 최소 구하기
 */