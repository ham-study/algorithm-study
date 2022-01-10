import java.util.*;

class PGMS42626 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int num : scoville) {
            pq.add(num);
        }
        
        for (int i=0; ;i++) {
            
            if (!pq.isEmpty() && pq.peek() >= K) {
                return i;
            }
            
            if (pq.size() <= 1) {
                break;
            }
            
            int num1 = pq.poll();
            int num2 = pq.poll();
            
            pq.add(num1 + num2*2);
        }
        return -1;
    }
}
/**
 * 소요시간: 10분
 * 시간복잡도: O(NlogN)
 * 공간복잡도: O(N)
 * 
 */