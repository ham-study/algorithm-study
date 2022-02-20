import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    private static int[][] ingredients;
    private static Stack<Integer> tempStack;
    private static List<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        ingredients = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int sour = Integer.parseInt(input[0]);
            int bitter = Integer.parseInt(input[1]);

            ingredients[i][0] = sour;
            ingredients[i][1] = bitter;
        }

        answer = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            bruteForce(1, 0, stack, i);
        }

        System.out.println(answer.stream().mapToInt(i -> i).min().getAsInt());
    }

    public static void bruteForce(int s, int b, Stack<Integer> stack, int quantity) {
        if (quantity == 0) {
            answer.add(Math.abs(b - s));
            return;
        }

        for (int i = 0; i < ingredients.length; i++) {
            if (!stack.contains(i)) {
                tempStack = new Stack<>();
                tempStack.addAll(stack);
                tempStack.add(i);
                bruteForce(s * ingredients[i][0], b + ingredients[i][1], tempStack, quantity - 1);
            }
        }
    }

}
