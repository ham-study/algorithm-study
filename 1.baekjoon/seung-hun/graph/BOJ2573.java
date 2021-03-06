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
1. melt -> boolean: ??? ???????????? ????????? ???????????????????
    ????????? ???????????? ????????? ????????? ?????????, ??? ????????? ?????? ???
- 0 ????????? ??? ??? ??????
- ?????? ???????????? ??????, ?????? ?????? ????????? ???????????? ???
2. divided -> boolean: ??? ????????? ???????????? ?????? ????????????
- bfs
*/

/**
 * ????????????: 25???
 * ???????????????: O(N*M*@)
 * ???????????????: O(N*M)
 */