class PGMS60059 {
    private static int empty;
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = lock[0].length;
        
        for (int[] l : lock) {
            for (int num : l) {
                if (num == 0) {
                    empty++;
                }
            }
        }
        
        for (int k=0;k<4;k++) {
            for (int i=-n;i<=n;i++) {
                for (int j=-m;j<=m;j++) {
                    if (check(i, j, key, lock)) {
                        return true;
                    }
                }
            }
            
            key = rotate(key);
        }
        
        
        return false;
    }
    
    private boolean check(int offsetR, int offsetC, int[][] key, int[][] lock) {
        int n = key.length;
        int m = key[0].length;
        int count = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                int row = i + offsetR, col = j + offsetC;
                if (key[i][j] == 0) continue;
                if (!checkRange(row, col, lock.length, lock[0].length)) continue;
                if (lock[row][col] != 0) return false;
                count++;
            }
        }
        
        return empty == count;
    }
    
    private static boolean checkRange(int row, int col, int n, int m) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }
    
    private int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] temp = new int[m][n];
        
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                temp[j][n-1-i] = arr[i][j];
            }
        }
        
        return temp;
    }
}
/**
 * 소요시간: 18분
 * 시간복잡도: O(N^2 * M^2)
 * 공간복잡도: O(N^2)
 */