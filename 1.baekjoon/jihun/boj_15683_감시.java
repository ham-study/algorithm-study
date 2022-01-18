/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/15683
 풀이시간 : 2h
 시간복잡도 : O(m*n * 4) = O(8*8 * 4) = O(256)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

class Coordinate {
    int y, x;
    int cctvNum;

    Coordinate(int y, int x, int cctvNum) {
        this.y = y;
        this.x = x;
        this.cctvNum = cctvNum;
    }
}

public class Main {
    private static int n, m;
    private static int notWallCnt;
    private static int[][] map;
    private static int min4AngleCnt = 64;

    private static List<Coordinate> cctvs = new ArrayList<>();
    private static int cctvNum;

    private static Map<Integer, Integer> directions = new HashMap<>(5); // dir : 개수

    private static int traceTmpNum = 7;

    public static void main(String[] args) throws Exception {
        init();

        solve(0, new int[cctvNum]); // 조합 문제

        System.out.print(min4AngleCnt);
    }

    private static void solve(int idx, int[] dirs) { // 가능한 방향 개수로 조합을 구함
        if (idx == cctvNum) {
            min4AngleCnt = Math.min(min4AngleCnt, putCctv(dirs));

            return;
        }

        var coor = cctvs.get(idx);
        int dirNum = directions.get(coor.cctvNum);

        for (int dir = 1; dir <= dirNum; dir++) {
            dirs[idx] = dir;
            solve(idx + 1, dirs);
        }
    }

    private static int putCctv(int[] dirs) {
        int viewedCnt = 0;
        for (int i = 0; i < cctvNum; i++) {
            var cctv = cctvs.get(i);

            int curViewedCnt = 0;
            switch (cctv.cctvNum) {
                case 1:
                    curViewedCnt = putCctv1(cctv.y, cctv.x, dirs[i]);
                    break;
                case 2:
                    curViewedCnt = putCctv2(cctv.y, cctv.x, dirs[i]);
                    break;
                case 3:
                    curViewedCnt = putCctv3(cctv.y, cctv.x, dirs[i]);
                    break;
                case 4:
                    curViewedCnt = putCctv4(cctv.y, cctv.x, dirs[i]);
                    break;
                case 5:
                    curViewedCnt = putCctv5(cctv.y, cctv.x, dirs[i]);
                    break;
            }
            viewedCnt += curViewedCnt;
        }

        traceTmpNum++;

        return notWallCnt - viewedCnt;
    }

    private static int putCctv5(int y, int x, int dir) {
        return putCctv1(y, x, 1) + putCctv1(y, x, 2) + putCctv1(y, x, 3) + putCctv1(y, x, 4);
    }

    private static int putCctv4(int y, int x, int dir) {
        if (dir == 1) {
            return putCctv1(y, x, 1) + putCctv1(y, x, 2) + putCctv1(y, x, 4);
        }

        if (dir == 2) {
            return putCctv1(y, x, 1) + putCctv1(y, x, 2) + putCctv1(y, x, 3);
        }

        if (dir == 3) {
            return putCctv1(y, x, 2) + putCctv1(y, x, 3) + putCctv1(y, x, 4);
        }

        if (dir == 4) {
            return putCctv1(y, x, 3) + putCctv1(y, x, 4) + putCctv1(y, x, 1);
        }
        System.out.println("로직오류 in cctv4");
        return -1;
    }

    private static int putCctv3(int y, int x, int dir) {
        if (dir == 1) {
            return putCctv1(y, x, 1) + putCctv1(y, x, 4);
        }

        if (dir == 2) {
            return putCctv1(y, x, 1) + putCctv1(y, x, 2);
        }

        if (dir == 3) {
            return putCctv1(y, x, 2) + putCctv1(y, x, 3);
        }

        if (dir == 4) {
            return putCctv1(y, x, 3) + putCctv1(y, x, 4);
        }
        System.out.println("로직오류 in cctv3");
        return -1;
    }

    private static int putCctv2(int y, int x, int dir) {
        if (dir == 1) {
            return putCctv1(y, x, 2) + putCctv1(y, x, 4);
        }

        if (dir == 2) {
            return putCctv1(y, x, 1) + putCctv1(y, x, 3);
        }
        System.out.println("로직오류 in cctv2");
        return -1;
    }

    private static int putCctv1(int y, int x, int dir) {
        //dir 1(위), 2(왼), 3(아래), 4(오른쪽)
        int cnt = 0;

        if (dir == 1) {
            for (int i = y; i >= 0; i--) {
                if (map[i][x] == 6) return cnt;
                if (map[i][x] == traceTmpNum) continue;
                map[i][x] = traceTmpNum;
                cnt++;
            }
            return cnt;
        }

        if (dir == 3) {
            for (int i = y; i < m; i++) {
                if (map[i][x] == 6) return cnt;
                if (map[i][x] == traceTmpNum) continue;
                map[i][x] = traceTmpNum;
                cnt++;
            }
            return cnt;
        }

        if (dir == 2) {
            for (int i = x; i >= 0; i--) {
                if (map[y][i] == 6) return cnt;
                if (map[y][i] == traceTmpNum) continue;
                map[y][i] = traceTmpNum;
                cnt++;
            }
            return cnt;
        }

        if (dir == 4) {
            for (int i = x; i < n; i++) {
                if (map[y][i] == 6) return cnt;
                if (map[y][i] == traceTmpNum) continue;
                map[y][i] = traceTmpNum;
                cnt++;
            }
            return cnt;
        }
        System.out.println("로직오류 in cctv1");
        return -1;
    }

    private static void init() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];

        int wallCnt = 0;
        for (int y = 0; y < m; y++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int x = 0; x < n; x++) {
                int val = Integer.parseInt(st.nextToken());

                if (val == 6) {
                    wallCnt++;
                }

                map[y][x] = val;

                if (val >= 1 && val <= 5) {
                    cctvs.add(new Coordinate(y, x, val));
                }
            }
        }

        notWallCnt = n * m - wallCnt;
        cctvNum = cctvs.size();

        br.close();

        directions.put(1, 4);
        directions.put(2, 2);
        directions.put(3, 4);
        directions.put(4, 4);
        directions.put(5, 1);
    }

}
