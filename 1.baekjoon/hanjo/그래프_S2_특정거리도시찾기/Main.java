package 그래프_S2_특정거리도시찾기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 정점 수
        int m = Integer.parseInt(st.nextToken());   // 간선 수
        int k = Integer.parseInt(st.nextToken());   // 거리 정보
        int x = Integer.parseInt(st.nextToken());   // 출발 정점

        int[][] edges = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(n, m, k, x, edges);

    }

    // 프로그래머스처럼 주어진다고 가정
    public static void solution(int n, int m, int k, int x, int[][] edges) {

        // 모든 인덱스는 정점에 맞춰 1부터 시작함

        // 인접리스트 생성
        ArrayList<Integer>[] edgeList = new ArrayList[n+1];
        edgeList[0] = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            edgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            edgeList[from].add(to);
        }

        // 정점 정보 초기화
        int[][] vertexInfo = new int[n+1][2];
        for(int i=1; i<n+1; i++){
            vertexInfo[i][0] = Integer.MAX_VALUE;   // 가중치
            vertexInfo[i][1] = 0;   // 방문여부
        }

        // bfs를 위한 큐 생성
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        vertexInfo[x][0] = 0;
        vertexInfo[x][1] = 1;

        // 다익스트라 bfs 
        ArrayList<Integer> correctVertex = new ArrayList<>();
        while(!queue.isEmpty()){
            int curV = queue.poll();

            for(int nextV : edgeList[curV]){
                if(vertexInfo[nextV][1] == 0 && vertexInfo[nextV][0] >= vertexInfo[curV][0] ){
                    queue.offer(nextV);
                    // 방문표시
                    vertexInfo[nextV][1] = 1;
                    // 누적거리 업데이트
                    vertexInfo[nextV][0] = vertexInfo[curV][0] + 1;
                    if(vertexInfo[nextV][0] == k){
                        correctVertex.add(nextV);
                    }
                }
            }
        }

        // 출력
        if(correctVertex.isEmpty()){
            System.out.println(-1);
        }
        else{
            Collections.sort(correctVertex);
            for(int v : correctVertex){
                System.out.println(v);
            }
        }
    }
}
