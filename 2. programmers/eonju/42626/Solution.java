import java.util.PriorityQueue;

class Solution {

    public int solution(int[] scoville, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            queue.add(scoville[i]);
        }

        return calculateScoville(queue, k);
    }

    public static int calculateScoville(PriorityQueue<Integer> queue, int k) {
        int count = 0;

        while (queue.size() >= 2 && queue.peek() < k) {
            Integer first = queue.poll();
            Integer second = queue.poll();

            queue.add(first + second * 2);
            count++;
        }

        if (queue.peek() < k) {
            return -1;
        }

        return count;
    }
}
/**
 * 시간: 11분
 * 시간 복잡도: O(N)
 * 공간 복잡도: -
 */
