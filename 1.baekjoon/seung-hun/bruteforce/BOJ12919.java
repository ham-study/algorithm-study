import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12919 {
    private static String S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        S = br.readLine();
        T = br.readLine();
        
        System.out.println(dfs(T));
    }

    private static int dfs(String str) {
        if (str.length() == S.length()) {
            return str.equals(S) ? 1 : 0;
        }
        int result = 0;
        if (str.charAt(str.length() - 1) == 'A') {
            result = Math.max(result, dfs(str.substring(0, str.length() - 1)));
            if (str.charAt(0) == 'B') {
                result = Math.max(result, dfs(new StringBuilder(str).reverse().substring(0, str.length() - 1)));
            }

        } else {
            if (str.charAt(0) == 'B') {
                result = Math.max(result, dfs(new StringBuilder(str).reverse().substring(0, str.length() - 1)));
            } else {
                return 0;
            }
        }
        return result;
    }
}

/**
 * 시간: 12분
 * 시간 복잡도: O(T)
 * 공간 복잡도: O(T)
 */