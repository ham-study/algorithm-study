package 이분탐색_G5_2110;

import java.io.*;
import java.util.*;

public class Main {

    public static int solution(int n, int c, int[] map) {

        Arrays.sort(map);

        // 공유기 최소거리 이분탐색
        int left = 1;
        int right = map[n - 1] - map[0];
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            System.out.println("-----------");
            System.out.println(mid);

            int lastLoc = map[0];
            int wifiCount = 1;

            for (int i = 0; i < n; i++) {
                int curLoc = map[i];
                // 공유기 설치
                if (mid <= curLoc - lastLoc) {
                    lastLoc = curLoc;
                    wifiCount++;
                }
            }

            // 공유기 최소거리 단축
            if (wifiCount < c) {
                right = mid - 1;
            } 
            else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] map = new int[n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, c, map));
    }

}