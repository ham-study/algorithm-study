import java.util.*;
import java.io.*;

public class BOJ15683 {
    static class Cctv {
        public final int row, col, seq;
        Cctv(int row, int col, int seq) {
            this.row = row;
            this.col = col;
            this.seq = seq;
        }
    }
    
    private static final int INF = 65;
    private static int[] dr = {1, -1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};
    private static int[][][] dirs = {
        {}, 
        {{0}, {1}, {2}, {3}}, 
        {{0, 1}, {2, 3}},
        {{0, 2}, {3, 0}, {2, 1}, {1, 3}},
        {{0, 2, 1}, {0, 2, 3}, {1, 3, 0}, {1, 3, 2}}, {{0, 1, 2, 3}}};
    private static List<Cctv> cctvs;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		cctvs = new ArrayList<>();
		
		for (int i=0;i<N;i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j=0;j<M;j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    
		        if (arr[i][j] > 0 && arr[i][j] < 6) {
		            cctvs.add(new Cctv(i, j, arr[i][j]));
		        }
		    }
		}
		
		System.out.println(dfs(0, arr));
	}
	
	private static int dfs(int idx, int[][] arr) {
	    
	    if (idx == cctvs.size()) {
	        return countDeadZones(arr);
	    }
	    
	    int[][] temp = copyOf(arr);
	    
	    Cctv cctv = cctvs.get(idx);
	    int result = INF;
	    for (int[] dir : dirs[cctv.seq]) {
	        move(temp, cctv.row, cctv.col, dir);
	        result = Math.min(result, dfs(idx + 1, temp));
	        rollback(temp, arr);
	    }
	    
	    return result;
    }

    private static void rollback(int[][] temp, int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            for (int j=0;j<arr[0].length;j++) {
                temp[i][j] = arr[i][j];
            }
        }
    }
    
    private static void move(int[][] arr, int row, int col, int[] dir) {
        int height = arr.length;
        int width = arr[0].length;
        for (int i : dir) {
            int nr = row, nc = col;
            
            while (checkRange(nr, nc, height, width)) {
                
                if (arr[nr][nc] == 6) break;
                if (arr[nr][nc] == 0) {
                    arr[nr][nc] = -1;    
                }
                
                nr += dr[i];
                nc += dc[i];
            }

        }
    }
    
    private static boolean checkRange(int row, int col, int height, int width) {
        return 0 <= row && row < height && 0 <= col && col < width;
    }
    
    private static int[][] copyOf(int[][] arr) {
        int[][] temp = new int[arr.length][arr[0].length];
        
        for (int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                temp[i][j] = arr[i][j];
            }
        }
        
        return temp;
    }
    
    private static int countDeadZones(int[][] arr) {
        int count = 0;
        for (int[] a : arr) {
            for (int num : a) {
                if (num == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}

/**
 * 
 * 
 */
/**
 * cctv 
 *  row, col, seq
 * ccvts array -> 방향 별로
 * dfs(idx, arr)
 *  if (idx == cctvs.length)
 *      count 0 in arr
 *      return
 * 
 *  temp = copyOf(arr)
 *  cctv = cctvs[idx]
 *  
 *  for dir <- dirs[cctv.seq]
 *      move(temp, cctv.row, cctv.col, dir)
 *      dfs(idx + 1, temp)
 *      rollback(temp, arr)
 *
 */
 