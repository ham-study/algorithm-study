import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main {

    private static String input;
    private static List<String> odds = new ArrayList<>(
        Arrays.asList("1", "3", "5", "7", "9")
    );

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        input = bufferedReader.readLine();
        int[] answer = solution();

        System.out.println(answer[0] + " " + answer[1]);

    }

    public static int[] solution() {
        int[] answer = new int[2];

        if (input.length() == 1) {
            int count = countOdd(input);

            answer[0] = count;
            answer[1] = count;
        } else if (input.length() == 2 || input.length() == 3) {
            String temp = input;
            int count = 0;

            while (temp.length() > 1) {
                count += countOdd(temp);
                String[] split = temp.split("");
                int sum = 0;
                for (String number : split) {
                    sum += Integer.parseInt(number);
                }
                temp = String.valueOf(sum);
            }
            count += countOdd(temp);

            answer[0] = count;
            answer[1] = count;
        } else {
            ArrayList<Integer> counts = new ArrayList<>();

            for (int i = 1; i <= input.length() - 2; i++) {
                for (int j = i + 1; j < input.length() - 1; j++) {
                    String temp = input;
                    while (temp.length() > 1) {
                        String sub1 = temp.substring(0, i);
                        String sub2 = temp.substring(i, j);
                        String sub3 = temp.substring(j);


                    }
                }
            }

            Collections.sort(counts);
            answer[0] = counts.get(0);
            answer[1] = counts.get(counts.size() - 1);
        }

        return answer;
    }

    public static int countOdd(String number) {
        String[] split = number.split("");
        int count = 0;

        for (String s : split) {
            count += Collections.frequency(odds, s);
        }

        return count;
    }
}
