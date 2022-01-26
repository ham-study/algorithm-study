import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static int n;
    private static int[] chessMap;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        chessMap = new int[n];
        count = 0;
        dfs(0);
        System.out.println(count);
        bufferedReader.close();
    }

    public static void dfs(int depth) {
        if (depth == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            chessMap[depth] = i;
            if (possible(depth)) {
                dfs(depth + 1);
            }
        }
    }

    public static boolean possible(int col) {
        for (int i = 0; i < col; i++) {
            if (chessMap[col] == chessMap[i]) {
                return false;
            } else if (Math.abs(col - i) == Math.abs(chessMap[col] - chessMap[i])) {
                return false;
            }
        }

        return true;
    }
}
