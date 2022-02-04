/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/10816
 풀이시간 : 10m
 시간복잡도 : O(mlogn) = O(500,000log500,000)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] inputs;
    private static int[] commands;

    public static void main(String[] args) throws IOException {
        init();

        var sb = new StringBuilder();

        for (var command : commands) {
            int lowerIdx = lowerbound(command);
            int upperIdx = upperbound(command);

            sb.append(upperIdx - lowerIdx).append(' ');
        }

        System.out.print(sb.toString());
    }

    private static int lowerbound(int target) {
        int low = 0;
        int high = n;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (inputs[mid] >= target) high = mid;
            else low = mid + 1;
        }

        return low;
    }

    private static int upperbound(int target) {
        int low = 0;
        int high = n;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (inputs[mid] > target) high = mid;
            else low = mid + 1;
        }

        return low;
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        inputs = new int[n];

        var st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        commands = new int[m];

        for (int i = 0; i < m; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        Arrays.sort(inputs);
    }


}
