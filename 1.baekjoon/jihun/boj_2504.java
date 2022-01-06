/**
 * https://www.acmicpc.net/problem/2504
 */

import java.io.*;
import java.util.*;

public class Main {
    private static char[] input;

    public static void main(String[] args) throws IOException {
        init();

        int answer = solve();

        System.out.print(answer);
    }

    private static int solve() {
        if (input.length % 2 == 1) {
            return 0;
        }

        Stack<String> stack = new Stack<>();

        for (var curCh : input) {
            if (curCh == '(' || curCh == '[') {
                stack.push(curCh + "");
                continue;
            }

            if (stack.isEmpty()) {
                return 0;
            }

            int tmpSum = 0;
            while (!stack.isEmpty()) { // 숫자라면 다 더함
                if (isBracket(stack.peek())) {
                    tmpSum = tmpSum == 0 ? 1 : tmpSum;
                    break;
                }
                tmpSum += Integer.parseInt(stack.pop());
            }

            if (stack.isEmpty() || !isValid(curCh, stack.pop())) {
                return 0;
            }

            if (curCh == ')') {
                stack.push((tmpSum * 2) + "");
            } else {
                stack.push((tmpSum * 3) + "");
            }
        }

        // 정답 구하기
        int result = 0;
        for (var val : stack) {
            if (isBracket(val)) {
                return 0;
            }
            result += Integer.parseInt(val);
        }

        return result;
    }

    private static boolean isValid(char curCh, String topBrac) {
        if (curCh == ')' && topBrac.equals("(")) return true;
        else if (curCh == ']' && topBrac.equals("[")) return true;
        else return false;
    }

    private static boolean isBracket(String str) {
        return str.equals("(") || str.equals("[");
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();

        br.close();
    }
}
/**
 * 소요시간: 3시간
 * 시간복잡도: O(N) : 입력 길이만큼 돌고 각 입력마다 특별히 N을 넘는 연산은 하지 않음
 * 공간복잡도: O(N)
 */
