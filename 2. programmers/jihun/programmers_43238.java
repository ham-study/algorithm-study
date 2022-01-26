/******************************************************************************

 문제출처 : https://programmers.co.kr/learn/courses/30/lessons/43238
 풀이시간 : 1h
 시간복잡도 : O(log(n*1,000,000,000))
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long answer = solve(n, times);

        return answer;
    }

    private long solve(int n, int[] times) {
        long low = 0;
        long high = (long) n * times[times.length - 1];

        while (low <= high) {
            long mid = (low + high) >>> 1;

            long tokenSum = getTokenSum(mid, times);

            if (tokenSum >= n) {  // lower bound
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long getTokenSum(long mid, int[] times) {
        long tokenSum = 0;

        for (int time : times) {
            tokenSum += (mid / time);
        }

        return tokenSum;
    }
}
