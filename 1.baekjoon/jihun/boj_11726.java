/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/11726
 풀이시간 : 30분
 시간복잡도 : O(n) (n: 입력 길이) 입력 길이만큼 반복문을 돌기 때문(bottom-up)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[] dp = new int[size + 1];
        dp[1] = 1;
        dp[2] = 2;

        if (size == 1 || size == 2) {
            System.out.println(dp[size]);
            return;
        }

        for (int i = 3; i <= size; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        System.out.println(dp[size]);
    }

}
