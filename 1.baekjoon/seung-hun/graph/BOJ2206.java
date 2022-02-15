import java.io.*;
import java.util.*;
public class BOJ2206 {
    private static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int[][] board;
    
    private static int N, M;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        
        for (int i=0;i<N;i++) {
            String[] line = br.readLine().split("");
            for (int j=0;j<M;j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }
        
        System.out.println(bfs());
    }
    
    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];    
        visited[0][0][0] = true;
        q.add(new int[] {0, 0, 1, 0});
        
        while (!q.isEmpty()) {
            int row = q.peek()[0];
            int col = q.peek()[1];
            int weight = q.peek()[2];
            int wall = q.poll()[3];
            
            if (row == N - 1 && col == M - 1) {
                return weight;
            }
            
            for (int i=0;i<4;i++) {
                int nr = row + d[i][0], nc = col + d[i][1];
                
                if (!checkRange(nr, nc)) continue;
                if (board[nr][nc] == 1 && wall != 1 && !visited[nr][nc][1]) {
                    visited[nr][nc][1] = true;
                    q.add(new int[] {nr, nc, weight + 1, 1});
                } else if (board[nr][nc] == 0 && !visited[nr][nc][wall]) {
                    visited[nr][nc][wall] = true;
                    q.add(new int[] {nr, nc, weight + 1, wall});
                }
                
            }
        }
        
        return -1;
    }
    
    private static boolean checkRange(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M;
    }
}
/***
 * 소요시간: 25분
 * 시간복잡도: O(N*M*2)
 * 공간복잡도: O(N*M*2)
 * 
 */
/**
1. board[row][col] == 1 > 벽을 부순적이 없고, 방문한적이 없을 떄
2.board[row][col] == 0 > 방문한적이 없을 떄
*/