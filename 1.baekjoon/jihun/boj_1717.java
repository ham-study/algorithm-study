/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/1717
 풀이시간 : 10m
 시간복잡도 : O(mlogn) ~ O(100,000 log(1,000,000)) ~ O(600,000)
 공간복잡도 : O(n)

 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] cycleMap;

    private final static String YES = "YES";
    private final static String NO = "NO";

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cycleMap = new int[n + 1];
        for (int i = 0; i <= n; i++) cycleMap[i] = i;

        var sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(a, b);
                continue;
            }

            sb.append(find(a) == find(b) ? YES : NO).append('\n');
        }

        System.out.print(sb.toString());
    }

    private static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        cycleMap[parentA] = parentB;
    }

    private static int find(int v) {
        if (cycleMap[v] == v) return v;

        int parent = find(cycleMap[v]);
        cycleMap[v] = parent; // 미리 부모를 초기화해두면 더 짧은 사간으로 연산 가능

        return parent;
    }
}
