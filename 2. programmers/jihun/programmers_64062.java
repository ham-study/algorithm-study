/******************************************************************************

 문제출처 : https://programmers.co.kr/learn/courses/30/lessons/64062
 풀이시간 : 30m
 시간복잡도 : O(log2억 * 200,000) ~ O(1억 6천)
 공간복잡도 : O(n)
 
 *******************************************************************************/

class Solution {
    public int solution(int[] stones, int k) {
        int low = 1;
        int high = 200000001; // 2억

        while (low < high) {
            int mid = (low + high) >>> 1;

            int adjZeroCnt = getAdjZeroCnt(stones, mid, k);

            if (adjZeroCnt > k - 1) high = mid; //upper bound
            else low = mid + 1;
        }

        return low;
    }

    private int getAdjZeroCnt(int[] stones, int mid, int k) {
        int adjZeroCnt = 0;

        for (var stone : stones) {
            if (stone - mid <= 0) adjZeroCnt++;
            else adjZeroCnt = 0;

            if (adjZeroCnt >= k) return k;
        }

        return adjZeroCnt;
    }
}
