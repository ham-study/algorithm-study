import java.util.Arrays;

class Solution {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int remove = 0;
        int lastRock = 0;

        int left = 1;
        int right = distance;
        int mid;

        Arrays.sort(rocks);

        while (left <= right) {
            mid = (left + right) / 2;

            for (int i = 0; i < rocks.length; i++) {
                if (mid > rocks[i] - lastRock) {
                    remove++;
                } else {
                    lastRock = rocks[i];
                }
            }

            if (distance - lastRock < mid) {
                remove++;
            }

            if (remove > n) {
                right = mid - 1;
            } else {
                answer = Math.max(mid, answer);
                left = mid + 1;
            }

            remove = 0;
            lastRock = 0;
        }
        return answer;
    }
}
