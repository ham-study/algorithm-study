class PGMS43238 {
    public long solution(int n, int[] times) {
        long left = 0, right = Long.MAX_VALUE;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (!check(times, n, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
    
    private boolean check(int[] times, int n, long base) {
        long count = 0;
        
        for (int time : times) {
            count += (base / time);
            
            if (count >= n) { // overflow check
                return true;
            }
        }
        
        return false;
    }
}
/**
소요시간: 35분
시간복잡도: O(Nlog(Long.MAX_VALUE))
공간복잡도: O(N);
*/
/**
7 10 14 20 21 28 30
7 14 21 28 35 42
7 10 14 21 28 35
*/