import java.io.*;

public class BOJ9663 {
    private static int N;
    private static int[] cols;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        cols = new int[N];
        System.out.println(dfs(0));
        
    }
    
    private static int dfs(int depth) {
        if (depth == N) {
            return 1;
        }
        int result = 0;
        for (int i=0;i<N;i++) {
            if (check(depth, i)) {
                cols[depth] = i;
                result += dfs(depth + 1);
                cols[depth] = 0;
            }
        }
        
        return result;
    }
    
    private static boolean check(int row, int col) {
        for (int i=0;i<row;i++) {
            if (cols[i] == col) {
                return false;
            }
            
            if ((row - i) == Math.abs(cols[i] - col)) {
                return false;
            }
        }
        
        return true;
    }
}
/**
 * 소요시간: 15분
 * 시간복잡도: O(2^N^2) 
 * 공간복잡도: O(2^N^2) (call 스택?)
 * 
 */
/**
 * 같은 열, 같은 행, 같은 대각선 x
 * 행 검사 -> depth + 1
 * 열 검사 -> 이전 행의 열들 중 같은 열X
 * 대각선 검사 -> 가로 == 세로 X
 * 
 */