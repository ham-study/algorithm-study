/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/14499
 풀이시간 : 30m
 시간복잡도 : O(n) = O(1,000) (n = 명령어 개수)
 공간복잡도 : O(n)

 *******************************************************************************/

import java.util.*;
import java.io.*;

class Dice {
    int y, x;

    int left, right;
    int bottom, top;
    int front, back;

    Dice(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void rollRight() {
        int tmp = this.right;
        this.right = this.top;
        this.top = this.left;
        this.left = this.bottom;
        this.bottom = tmp;

        this.x++;
    }

    public void rollLeft() {
        int tmp = this.left;
        this.left = this.top;
        this.top = this.right;
        this.right = this.bottom;
        this.bottom = tmp;

        this.x--;
    }

    public void rollUp() {
        int tmp = this.back;
        this.back = this.top;
        this.top = this.front;
        this.front = this.bottom;
        this.bottom = tmp;

        this.y--;
    }

    public void rollDown() {
        int tmp = this.front;
        this.front = this.top;
        this.top = this.back;
        this.back = this.bottom;
        this.bottom = tmp;

        this.y++;
    }
}

public class Main {
    private static int n, m;
    private static int startX, startY;
    private static int commandNum;

    private static int map[][];

    private static List<Integer> commands;

    public static void main(String[] args) throws Exception {
        init();

        Dice dice = new Dice(startY, startX);
        var result = new StringBuilder();

        for (int command : commands) { //동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4

            switch (command) {
                case 1:
                    goRight(dice, result);
                    break;
                case 2:
                    goLeft(dice, result);
                    break;
                case 3:
                    goUp(dice, result);
                    break;
                case 4:
                    goDown(dice, result);
                    break;
            }
        }

        System.out.print(result.toString());
    }

    private static void goRight(Dice dice, StringBuilder sb) {
        if (dice.x + 1 >= m) return;

        dice.rollRight();

        updateMapOrDice(dice, sb);
    }

    private static void goLeft(Dice dice, StringBuilder sb) {
        if (dice.x - 1 < 0) return;

        dice.rollLeft();

        updateMapOrDice(dice, sb);
    }

    private static void goUp(Dice dice, StringBuilder sb) {
        if (dice.y - 1 < 0) return;

        dice.rollUp();

        updateMapOrDice(dice, sb);
    }

    private static void goDown(Dice dice, StringBuilder sb) {
        if (dice.y + 1 >= n) return;

        dice.rollDown();

        updateMapOrDice(dice, sb);
    }

    private static void updateMapOrDice(Dice dice, StringBuilder sb) {
        if (map[dice.y][dice.x] == 0) {
            map[dice.y][dice.x] = dice.bottom;
        } else {
            dice.bottom = map[dice.y][dice.x];
        }

        sb.append(dice.top).append('\n');
    }

    private static void init() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        commandNum = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        commands = new ArrayList<>(commandNum);

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < commandNum; i++) {
            commands.add(Integer.parseInt(st.nextToken()));
        }

        br.close();
    }

}
