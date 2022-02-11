/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/13164
 풀이시간 : 30m
 시간복잡도 : O(n)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n, k;
    private static List<Integer> inputs;

    public static void main(String[] args) throws IOException {
        init();

        int result = solve();

        System.out.print(result);
    }

    private static int solve() {
        var diff = new ArrayList<Integer>(n - 1);

        for (int i = 1; i < n; i++) {
            diff.add(inputs.get(i) - inputs.get(i - 1));
        }

        Collections.sort(diff);

        int result = 0;

        for (int i = 0; i < n - k; i++) {
            result += diff.get(i);
        }

        return result;
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        inputs = new ArrayList<>(n);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputs.add(Integer.parseInt(st.nextToken()));
        }

        br.close();
    }

}
