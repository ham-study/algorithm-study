import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] road = bufferedReader.readLine().split("");

        int answer = 0;
        int count = 0;
        String flag = road[0];
        for (int i = 1; i < n; i++) {
            if (!flag.equals(road[i])) {
                if (count == 0) {
                    count++;
                    flag = road[i];
                } else {
                    answer++;
                    count = 0;
                    flag = road[i];
                }
            }
        }

        System.out.println(answer + 1);
    }
}
