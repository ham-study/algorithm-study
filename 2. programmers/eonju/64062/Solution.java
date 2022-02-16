package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PMS_64062 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

    public static int solution(int[] stones, int k) {
        int answer = 0;
        int[] compareArray = Arrays.stream(stones)
            .distinct()
            .sorted()
            .toArray();

        for (int i = 0; i < compareArray.length; i++) {
            if (countContinuousZero(stones, compareArray[i]) == k) {
                answer = compareArray[i];
                break;
            }
        }

        return answer;
    }

    public static int countContinuousZero(int[] stones, int target) {
        ArrayList<Integer> counts = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < stones.length; i++) {
            if (stones[i] <= target) {
                stack.add(0);
            } else {
                counts.add(stack.size());
                stack.clear();
            }
        }

        return counts.stream().mapToInt(i -> i).max().getAsInt();
    }
}
