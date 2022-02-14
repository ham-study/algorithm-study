import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int childQuantity = Integer.parseInt(input[0]);
        int groupQuantity = Integer.parseInt(input[1]);

        int[] children = Arrays.stream(bufferedReader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        //차이 구하기
        dist = new int[childQuantity - 1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = children[i + 1] - children[i];
        }

        Arrays.sort(dist);

        int answer = 0;
        for (int i = 0; i < childQuantity - groupQuantity; i++) {
            answer += dist[i];
        }

        System.out.println(answer);
    }
}
