import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class BOJ2504 {
    private static Map<Integer, Integer> pair;
    private static Map<Integer, Integer> numMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        pair = Map.of(-2, -1 , -4, -3);
        numMap = Map.of(-1, 2, -3, 3);
        
        String string = br.readLine();

        int[] convert = new int[string.length()];

        for (int i=0;i<convert.length;i++) {
            switch(string.charAt(i)) {
                case '(':
                    convert[i] = -1;
                    break;
                case ')':
                    convert[i] = -2;
                    break;
                case '[':
                    convert[i] = -3;
                    break;
                case ']':
                    convert[i] = -4;
                    break;
            }
        }

        System.out.println(solve(convert));
    }
    
    private static int solve(int[] arr) {

        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (int num : arr) {
            if (num == -2 || num == -4) {
                
                int current = 0;

                while (!stack.isEmpty() && stack.peek() != pair.get(num)) {

                    if (stack.peek() == -1 || stack.peek() == -3) {
                        return 0;
                    }

                    current += stack.pop();
                }
                
                if (stack.isEmpty()) {
                    return 0;
                }

                current = current == 0 ? 1 : current;
                current *= numMap.get(stack.pop());
                stack.add(current);

            } else {
                stack.add(num);
            }
        }

        if (stack.contains(-1) || stack.contains(-3))
            return 0;

        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
} 

/***
 * 시간: 1H+
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 * 
 */