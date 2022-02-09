/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/12865
 풀이시간 : 
 시간복잡도 :
 공간복잡도 : 

 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n, k;
    private static int[][] dp;
    private static int[] W; // weight
    private static int[] V; // value

    public static void main(String[] args) throws IOException {
        init();

        int year = solve(n - 1, k);

        System.out.print(year);
    }

    private static int solve(int i, int k) {
        // i가 0미만, 즉 범위 밖이 된다면
        if (i < 0)
            return 0;

        // 탐색하지 않은 위치라면?
        if (dp[i][k] == 0) {

            // 현재 물건(i)을 추가로 못담는 경우 (이전 i값 탐색)
            if (W[i] > k) {
                dp[i][k] = solve(i - 1, k);
            }
            // 현재 물건(i)을 담을 수 있는 경우
            else {
                // 이전 i값과 이전 i값에 대한 k-W[i]의 값 + 현재 가치(V[i])중 큰 값을 저장
                dp[i][k] = Math.max(solve(i - 1, k), solve(i - 1, k - W[i]) + V[i]);
            }
        }
        return dp[i][k];
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        W = new int[n];
        V = new int[n];

        dp = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
    }

}
