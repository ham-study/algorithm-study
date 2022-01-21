import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static final int[] moveX = {0, 0, -1, 1}; // 동, 서, 북, 남
    private static final int[] moveY = {1, -1, 0, 0};
    private static final int EAST = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    private static final int SOUTH = 4;
    private static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        int height = info[0];
        int width = info[1];
        int x = info[2];
        int y = info[3];
        int commandQuantity = info[4];

        int[][] map = new int[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        int[] commands = Arrays.stream(bufferedReader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        for (int command : commands) {
            int nextX = x + moveX[command - 1];
            int nextY = y + moveY[command - 1];

            if (nextX >= 0 && nextY >= 0 && nextX < height && nextY < width) {
                rollDice(command);

                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = dice[6];
                } else {
                    dice[6] = map[nextX][nextY];
                    map[nextX][nextY] = 0;
                }

                x = nextX;
                y = nextY;
                System.out.println(dice[1]);
            }
        }

    }

    public static void rollDice(int command) {
        int[] temp = dice.clone();

        if (command == EAST) {
            dice[1] = temp[4];
            dice[3] = temp[1];
            dice[4] = temp[6];
            dice[6] = temp[3];

        } else if (command == WEST) {
            dice[1] = temp[3];
            dice[3] = temp[6];
            dice[4] = temp[1];
            dice[6] = temp[4];

        } else if (command == NORTH) {
            dice[1] = temp[5];
            dice[2] = temp[1];
            dice[5] = temp[6];
            dice[6] = temp[2];

        } else if (command == SOUTH) {
            dice[1] = temp[2];
            dice[2] = temp[6];
            dice[5] = temp[1];
            dice[6] = temp[5];

        }
    }
}
