package 완전탐색_G5_호석이두마리치킨;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 정점 수
        int m = Integer.parseInt(st.nextToken());   // 간선 수

        int[][] edges = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, edges));
    }

    public static final int INF = 100*100*100 + 1;

    public static String solution(int n, int[][] edges){

        // 정점간 최단거리 배열 초기화
        int[][] dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = INF;
            }
        }
        for(int[] edge : edges){
            dist[edge[0]][edge[1]] = 1;
            dist[edge[1]][edge[0]] = 1;
        }

        // 최단거리 구하기
        floydWarshall(n, dist);

        // 최단거리 가지고 어떻게?




        // for(int[] d : dist){
        //     System.out.println(Arrays.toString(d));
        // }

        return "";
    }

    public static void floydWarshall(int n, int[][] dist){
        // k - 거쳐야하는 정점
        // i - 출발 정점
        // j - 도착 정점
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    // 기존 i->j 비용과 i->k->j 비용을 비교
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
    
}
