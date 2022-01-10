import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] memorization = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        memorization[1] = 1 % 10007;
        memorization[2] = 2 % 10007;

        System.out.println(dp(n));
    }

    public static int dp(int n) {
        if (memorization[n] == 0) {
            memorization[n] = (dp(n - 1) + dp(n - 2)) % 10007;
        }
        return memorization[n];
    }
}
