import java.io.*;
import java.util.*;
public class BOJ2573 {
    private static int N, M;
    private static int[][] board;
    private static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int year = 1;
        while(true) {
            if (!melt()) {
                System.out.println(0);
                return;
            }
            
            if (divided()) {
                System.out.println(year);
                return;
            } 
            
            year++;
        }
    }
    
    private static boolean melt() {
        int[][] melted = new int[N][M];
        boolean flag = false;
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (board[i][j] == 0) continue;
                for (int k=0;k<4;k++) {
                    int nr = i + d[k][0], nc = j + d[k][1];
                    if (!checkRange(nr, N, nc, M)) continue;
                    if (board[nr][nc] != 0) continue;
                    melted[i][j]--;
                    flag = true;
                }
            }
        }
        
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                board[i][j] += melted[i][j];
                if (board[i][j] < 0) {
                    board[i][j] = 0;
                }
            }
        }
        
        return flag;
    }
    
    private static boolean divided() {
        boolean[][] visited = new boolean[N][M];
        int count = 0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (board[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j, visited);
                count++;
            }
        }
        
        // for (int[] b : board) {
        //     System.out.println(Arrays.toString(b));
        // }
        // System.out.println(count);
        return count >= 2;
    }
    
    private static void bfs(int row, int col, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        visited[row][col] = true;
        q.add(new int[] {row, col});
        
        while (!q.isEmpty()) {
            row = q.peek()[0];
            col = q.poll()[1];
            
            for (int i=0;i<4;i++) {
                int nr = row + d[i][0], nc = col + d[i][1];
                if (!checkRange(nr, N, nc, M)) continue;
                if (board[nr][nc] == 0 || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.add(new int[] {nr, nc});
            }
        }
    }
    
    private static boolean checkRange(int row, int R, int col, int C) {
        return 0 <= row && row < R && 0 <= col && col < C;
    }
}
/*
1. melt -> boolean: 단 하나라도 높이가 줄어들었는가?
    높이가 줄어들지 않으면 빙산이 없거나, 빈 공간이 없는 것
- 0 이상인 곳 네 방향
- 먼저 감소하지 말고, 감소 배열 만들어 감소시킬 것
2. divided -> boolean: 두 덩어리 이상으로 분리 되었는가
- bfs
*/

/**
 * 소요시간: 25분
 * 시간복잡도: O(N*M*@)
 * 공간복잡도: O(N*M)
 */