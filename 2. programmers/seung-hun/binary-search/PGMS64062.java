import java.util.*;
class PGMS64062 {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 1, right = k;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Map<Integer, Integer> counter = new HashMap<>();

        for (int i = 0; i < k; i++) {
            answer = Math.max(answer, stones[i]);
            pq.add(stones[i]);
            counter.putIfAbsent(stones[i], 0);
            counter.compute(stones[i], (key, value) -> value + 1);
        }

        for (; right < stones.length; right++) {
            counter.compute(stones[left-1], (key, value) -> value - 1);

            while (!pq.isEmpty() && counter.get(pq.peek()) == 0)
                pq.poll();

            pq.add(stones[right]);
            counter.putIfAbsent(stones[right], 0);
            counter.compute(stones[right], (key, value) -> value + 1);

            answer = Math.min(answer, pq.peek());

            left++;

        }        
        return answer;
    }

    public int solution2(int[] stones, int k) {
        int answer = 0;

        int left = 0, right = 200000001;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (cross(stones, mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return answer;
    }

    private boolean cross(int[] stones, int count, int limit) {
        int skip = 0;

        for (int stone : stones) {
            if (stone >= count) {
                skip++;
            } else {
                skip = 0;
            }

            if (skip == limit) {
                return true;
            }
        }

        return false;
    }
}