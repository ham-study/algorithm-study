import java.util.Arrays;

class Solution {

    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long answer = 0;
        long minTime = times[0];
        long maxTime = (long) n * times[times.length - 1];

        while (minTime <= maxTime) {
            long midTime = (minTime + maxTime) / 2;
            long people = 0;

            for (int time : times) {
                people += midTime / time;
            }

            if (people >= n) {
                maxTime = midTime - 1;
                answer = midTime;
            } else {
                minTime = midTime + 1;
            }
        }

        return answer;
    }
}
