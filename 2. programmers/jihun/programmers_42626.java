/******************************************************************************

 문제출처 : https://programmers.co.kr/learn/courses/30/lessons/42626
 풀이시간 : 30분
 시간복잡도 : O(n) (n: 입력 길이) 입력 길이만큼 반복문을 돌기 때문
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.util.*;

class Solution {
    private PriorityQueue<Long> pq = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        if(K == 0) return 0;
        
        addAll(scoville, K);
        
        int answer = 0;
        while(pq.size() >= 2 && pq.peek() < K){
            pq.add(pq.poll() + pq.poll() * 2);
            answer++;
        }
        
        if(pq.peek() < K) return -1;
    
        return answer;
    }
    
    private void addAll(int[] scoville, int K) {
        for(long val : scoville) {
            pq.add(val);
        }
        /*
        for(var a : pq){
            System.out.print(a + " ");
        }
        */
        
    }
}
