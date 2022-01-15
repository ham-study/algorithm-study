/******************************************************************************

문제출처 : https://programmers.co.kr/learn/courses/30/lessons/60059
풀이시간 : 10m
시간복잡도 : O(n^2) (n: 입력 길이) 
공간복잡도 : O(n)

*******************************************************************************/

class Solution {
    private int minX, minY, maxX, maxY;
    private int xSizeOfLock, ySizeOfLock;
    private int[][] newLock;
    
    public boolean solution(int[][] key, 
                           int[][] lock) {
        final int lockSize = getLockSizeAndsetCoordinationOfLock(lock);
        
        for(int y = 0; y <= lock.length - ySizeOfLock; y++){
            for(int x = 0; x <= lock.length - xSizeOfLock; x++){
                int emptyCountOfKey = getEmptyCountOfKey(key, y, x);
                
                if(emptyCountOfKey != lockSize) continue;
                
                if(isMatch(y, x, key, newLock)) return true;
                for(int rot = 0; rot < 4; rot++){
                    newLock = rotate90(newLock);
                    if(isMatch(y, x, key, lock)) return true;
                }
            }
        }
        
        boolean answer = true;
        return answer;
    }
    
    static int[][] rotate90(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n-1-j][i];
            }
        }

        return rotate;
    }
    
    private boolean isMatch(int y, int x, int[][] key, int[][] lock){
        int lockY = 0;
        int lockX = 0;
        for(int tmpy = y; tmpy < y + ySizeOfLock; tmpy++){
            for(int tmpx = x; tmpx < x + xSizeOfLock; tmpx++){
                if(key[tmpy][tmpx] == lock[lockY][lockX]) return false;
            }
            
            lockY++;
            lockX = 0;
        }
        
        return true;
    }
    
    private int getEmptyCountOfKey(int[][] key, int y, int x){
        int result = 0;
        
        for(int tmpY = y; tmpY < y + ySizeOfLock; tmpY++){
            for(int tmpX = x; tmpX < x + xSizeOfLock; tmpX++){
                if(key[tmpY][tmpX] == 1) result++;
            }
        }
        
        return result;
    }
    
    private int getLockSizeAndsetCoordinationOfLock(int[][] lock){
        int lockSize = 0;
        
        for(int y = 0; y < lock.length; y++){
            for(int x = 0; x < lock.length; x++){
                if(lock[y][x] == 1) continue;
                
                lockSize++;
                
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }
        
        xSizeOfLock = maxX - minX + 1;
        ySizeOfLock = maxY - minY + 1;
        
        int tmpSize = Math.max(xSizeOfLock, ySizeOfLock);
        newLock = new int[tmpSize][tmpSize];
        
        int yidx = 0;
        int xidx = 0;
        for(int fromy = minY; fromy <= maxY; fromy++){
            for(int fromx = minX; fromx <= maxX; fromx++){
                newLock[yidx][xidx++] = lock[fromy][fromx];
            }
            
            yidx++;
            xidx = 0;
        }
        
        return lockSize;
    }
}
