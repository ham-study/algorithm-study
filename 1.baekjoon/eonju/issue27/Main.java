import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String> answers;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        answers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = bufferedReader.readLine();
            makeAnagram(input);
        }

        for (String str : answers.stream().distinct().collect(Collectors.toList())) {
            System.out.println(str);
        }

    }

    public static void makeAnagram(String str) {
        String[] split = str.split("");
        for (int i = 0; i < str.length(); i++) {
            String target = split[i];
            String subString = str.substring(0, i) + str.substring(i+1);
            for (int j = 0; j < str.length(); j++) {
                StringBuffer sb = new StringBuffer();
                sb.append(subString);
                sb.insert(j, target);
                answers.add(String.valueOf(sb));
            }
        }
    }
}
