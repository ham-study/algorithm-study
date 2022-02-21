import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][] ingredients;
    private static boolean[] visited;
    private static int min = Integer.MAX_VALUE;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        ingredients = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int sour = Integer.parseInt(input[0]);
            int bitter = Integer.parseInt(input[1]);

            ingredients[i][0] = sour;
            ingredients[i][1] = bitter;
        }

        bruteForce(1, 0, 0);
        System.out.println(min);
    }

    public static void bruteForce(int s, int b, int idx) {
        if (idx == n) {
            min = Math.min(min, Math.abs(s - b));
            return;
        }

        bruteForce(s * ingredients[idx][0], b + ingredients[idx][1], idx + 1);
        bruteForce(s, b, idx + 1);

    }
}
