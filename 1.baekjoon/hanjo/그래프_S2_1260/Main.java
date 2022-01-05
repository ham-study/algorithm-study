package 그래프_S2_1260;

import java.util.*;

public class Main {

    static int n; // 정점 개수
    static int m; // 간선 개수
    static int v; // 시작 정점 번호
    static int[][] matrix; // 인접행렬
    static int[] visited; // 방문 정점 체크

    public static void dfs(int v) {
        visited[v - 1] = 1;
        System.out.print(String.format("%d ", v));

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0 && matrix[v - 1][i] == 1) {
                dfs(i + 1);
            }
        }
    }

    public static void bfs(int v) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v - 1] = 1;

        while (!queue.isEmpty()) {

            int curV = queue.poll();
            System.out.print(String.format("%d ", curV));

            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && matrix[curV - 1][i] == 1) {
                    queue.offer(i + 1);
                    visited[i] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        v = sc.nextInt();

        // int[][] edges = new int[m][2];
        // for (int i = 0; i < m; i++) {
        // edges[i][0] = sc.nextInt();
        // edges[i][1] = sc.nextInt();
        // }

        // 인접 행렬 생성
        matrix = new int[n][n];
        for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt() - 1;
            int v2 = sc.nextInt() - 1;
            matrix[v1][v2] = 1;
            matrix[v2][v1] = 1;
        }
        // 방문 리스트 초기화
        visited = new int[n];

        dfs(v);
        System.out.println();
        Arrays.fill(visited, 0);
        bfs(v);
    }
}

/**
 * 링크 : https://www.acmicpc.net/problem/1260
 * 성공여부 : X
 * 풀이시간 : 다시 공부하느라 2h
 * 시간복잡도 : 
 * 공간 복잡도 : 
 * 메모리 : 47628 KB
 * 소요 시간 : 780 ms
 * 
 * Arrays.fill() - 배열을 초기화시켜주는 메소드
 */