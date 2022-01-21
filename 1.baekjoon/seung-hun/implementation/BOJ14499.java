import java.io.*;
import java.util.*;
public class Main {
    private static final int UP = 0;
    private static final int FRONT = 1;
    private static final int RIGHT = 2;
    private static final int LEFT = 3;
    private static final int REAR = 4;
    private static final int DOWN = 5;
    
    private static final int EAST = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    private static final int SOUTH = 4;
    
    private static int[][] d = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private static int[] dice = {0, 0, 0, 0, 0, 0};
    private static int[][] board;
    private static int N, M, K;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<K;i++) {
            int direction = Integer.parseInt(st.nextToken());
            int nx = x + d[direction][0], ny = y + d[direction][1];
        
            if (!checkRange(nx, ny)) continue;
            
            moveDice(direction);
            
            if (board[nx][ny] == 0) {
                board[nx][ny] = dice[DOWN];
            } else {
                dice[DOWN] = board[nx][ny];
                board[nx][ny] = 0;
            }
            sb.append(dice[UP]).append("\n");
            x = nx;
            y = ny;
        }
        
        System.out.println(sb);
    }
    
    private static boolean checkRange(int x, int y) {
        
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    
    private static void moveDice(int direction) {
    
        switch(direction) {
            case EAST:
                moveEast();
                break;
            case WEST:
                moveWest();
                break;
            case NORTH:
                moveNorth();
                break;
            case SOUTH:
                moveSouth();
                break;
        }
        
    }
    
    private static void moveEast() {
        int temp = dice[UP];
        dice[UP] = dice[LEFT];
        dice[LEFT] = dice[DOWN];
        dice[DOWN] = dice[RIGHT];
        dice[RIGHT] = temp;
    }
    
    
    private static void moveWest() {
        int temp = dice[UP];
        dice[UP] = dice[RIGHT];
        dice[RIGHT] = dice[DOWN];
        dice[DOWN] = dice[LEFT];
        dice[LEFT] = temp;
    }
    
    
    private static void moveNorth() {
        int temp = dice[UP];
        dice[UP] = dice[FRONT];
        dice[FRONT] = dice[DOWN];
        dice[DOWN] = dice[REAR];
        dice[REAR] = temp;
    }
    
    
    private static void moveSouth() {
        int temp = dice[UP];
        dice[UP] = dice[REAR];
        dice[REAR] = dice[DOWN];
        dice[DOWN] = dice[FRONT];
        dice[FRONT] = temp;
    }
}
/**
 * 소요시간: 40분
 * 시간복잡도: O(K)
 * 공간복잡도: O(NM)
 * 
 * 
/***
 * 상전우좌후하
 * 1 2 3 4 5 6 
 * 
 * moveEast
 * 4 2 1 6 5 3
 * 
 * moveWest
 * 3 2 6 1 5 4
 * 
 * moveNorth
 * 2 6 3 4 1 5
 * 
 * moveSouth
 * 5 1 3 4 6 2
 * //
 * if board[nr][nc] == 0:
 *  board[nr][nc] = dice[DOWN]
 * else:
 *  dice[DOWN] = board[nr][nc]
 *  board[nr][nc] = 0
 * 
 */
 