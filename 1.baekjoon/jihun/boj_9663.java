/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/9663
 풀이시간 : 1h
 시간복잡도 : O(n^n) : isValid에서 많은 경우를 걸러주기 때문에 가능성의 거의 없음
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] combination = new int[15];
    private static int answer;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        backtrack(0);

        System.out.print(answer);
    }

    private static void backtrack(int index) {
        if (index == n) {
            answer++;

            return;
        }

        for (int num = 0; num < n; num++) {
            combination[index] = num;

            if (isValid(index)) {
                backtrack(index + 1);
            }
        }
    }

    private static boolean isValid(int index) {
        for (int i = 0; i < index; i++) {
            if (combination[i] == combination[index] || (index - i) == Math.abs(combination[i] - combination[index])) {
                return false;
            }
        }

        return true;
    }


}
