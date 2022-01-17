/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/9251
 풀이시간 : 2h
 시간복잡도 : O(n^2) (n <= 1,000)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static char[] input1, input2;

    public static void main(String[] args) throws Exception {
        setInitialValue();

        int answer = solve();

        System.out.print(answer);
    }

    private static int solve() {
        int[][] dp = new int[1001][1001]; // index 0 라인 사용 안함

        for (int y = 0; y < input1.length; y++) {
            for (int x = 0; x < input2.length; x++) {
                if (input1[y] == input2[x]) {
                    dp[y + 1][x + 1] = dp[y][x] + 1;
                    continue;
                }

                dp[y + 1][x + 1] = Math.max(dp[y][x + 1], dp[y + 1][x]);
            }
        }

        return dp[input1.length][input2.length];
    }

    private static void setInitialValue() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        input1 = br.readLine().toCharArray();
        input2 = br.readLine().toCharArray();

        br.close();
    }

}
