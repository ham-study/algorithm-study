/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/20164
 풀이시간 : 2h
 시간복잡도 : O(n^n) 
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;

public class Main {

    private static int n;
    private static int min = Integer.MAX_VALUE;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int size = getNumberSize(n);

        if (size <= 2) {
            int result = solveSizeUnderTwo(n);
            System.out.print(result + " " + result);
            return;
        }

        solve(n, size, 0);

        System.out.print(min + " " + max);
    }

    private static void solve(int num, int size, int sumOfOddCnt) { // num : 세 구역의 합
        if (getNumberSize(num) <= 2) {  // 세 자리수가 될 때까지
            int oddCntOfSizeThree = solveSizeUnderTwo(num);

            max = Math.max(sumOfOddCnt + oddCntOfSizeThree, max);
            min = Math.min(sumOfOddCnt + oddCntOfSizeThree, min);

            return;
        }

        int curOddCnt = getOddCnt(num);

        // 세 구역으로 쪼개기
        int token1, token2, token3;
        for (int firstOffset = 1; firstOffset <= size - 2; firstOffset++) {
            int powOfFirstOffset = (int) Math.pow(10, firstOffset);
            token1 = num % powOfFirstOffset;

            for (int secondOffset = firstOffset + 1; secondOffset <= size - 1; secondOffset++) {
                int powOfSecondOffset = (int) Math.pow(10, secondOffset);

                token2 = (num % powOfSecondOffset) / powOfFirstOffset;
                token3 = num / powOfSecondOffset;

                int sumOfTokens = token3 + token2 + token1;

                solve(sumOfTokens, getNumberSize(sumOfTokens), curOddCnt + sumOfOddCnt);
            }
        }

    }

    private static int getOddCnt(int num) { // 주어진 숫자에서 홀수의 개수 구하기
        int cnt = 0;

        while (true) {
            int rest = num % 10;

            if (rest % 2 == 1) cnt++;

            num = num / 10;

            if (num == 0) break;
        }

        return cnt;
    }

    private static int solveSizeUnderTwo(int num) {
        int result = 0;
        int token1 = 0;
        int token2 = 0;

        while (num <= 99 && num >= 10) {
            token1 = num % 10;
            token2 = (num / 10) % 10;

            if (token1 % 2 == 1) result++;
            if (token2 % 2 == 1) result++;

            num = token1 + token2;
        }

        if (num % 2 == 1) result++;

        return result;
    }

    private static int getNumberSize(int num) { // 자리수 계산
        int size = 1;

        while (true) {
            num = num / 10;

            if (num == 0) break;

            size++;
        }

        return size;
    }


}
