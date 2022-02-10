/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/15886
 풀이시간 : 10m
 시간복잡도 : O(n)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int len;
    private static String inputs;

    public static void main(String[] args) throws IOException {
        init();

        int result = solve();

        System.out.print(result);
    }

    private static int solve() {
        int result = 0;

        var defore = inputs.charAt(0);
        for (int i = 1; i < len; i++) {
            var cur = inputs.charAt(i);

            if (defore == 'E' && cur == 'W') {
                result++;
            }

            defore = cur;
        }

        return result;
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        len = Integer.parseInt(br.readLine());

        inputs = br.readLine();

        br.close();
    }

}
