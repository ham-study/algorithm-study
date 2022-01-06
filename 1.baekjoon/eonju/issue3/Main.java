import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static final int SMALL = 2;
    private static final int LARGE = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split("");
        int answer = 0;
        int temp = 1;

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            String target = input[i];

            if (target.equals("(")) {
                stack.push(target);
                temp *= SMALL;
            } else if (target.equals("[")) {
                stack.push(target);
                temp *= LARGE;
            } else if (target.equals(")")) {
                if (stack.isEmpty() || !stack.peek().equals("(")) {
                    answer = 0;
                    break;
                } else {
                    answer += temp;
                }
                stack.pop();
                temp /= SMALL;
            } else if (target.equals("]")) {
                if (stack.isEmpty() || !stack.peek().equals("[")) {
                    answer = 0;
                    break;
                } else {
                    answer += temp;
                }
                stack.pop();
                temp /= LARGE;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
/**
 * 시간: 1시간 10분(실패) 시간 복잡도: N² 공간 복잡도: -
 */
