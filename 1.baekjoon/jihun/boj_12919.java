/**
 * https://www.acmicpc.net/problem/12919
 */

import java.io.*;

public class Main {
    private static String S, T;

    private static final char A = 'A';
    private static final char B = 'B';

    public static void main(String[] args) throws IOException {
        init();

        var result = solve(T);

        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result ? "1" : "0");
        bw.flush();
        bw.close();
    }

    private static boolean solve(String curT) {
        if (S.length() == curT.length()) {
            if (S.equals(curT))
                return true;

            return false;
        }

        var firstCh = curT.charAt(0);
        var lastCh = curT.charAt(curT.length() - 1);

        if (firstCh == A && lastCh == A) {
            return solve(curT.substring(0, curT.length() - 1));
        } else if (firstCh == A && lastCh == B) {
            return false;
        } else if (firstCh == B && lastCh == A) {
            return solve(curT.substring(0, curT.length() - 1)) || solve(new StringBuilder(curT).reverse().deleteCharAt(curT.length() - 1).toString());
        } else if (firstCh == B && lastCh == B) {
            return solve(new StringBuilder(curT).reverse().deleteCharAt(curT.length() - 1).toString());
        }

        return false;
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();
    }

}
