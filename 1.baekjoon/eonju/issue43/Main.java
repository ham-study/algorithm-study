import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static final int[] moveX = {0, 0, -1, 1};
    private static final int[] moveY = {-1, 1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] info = bufferedReader.readLine().split(" ");
        int height = Integer.parseInt(info[0]);
        int width = Integer.parseInt(info[1]);

        int[][] map = new int[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        int answer = height * width;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {

                } else if (map[i][j] == 2) {

                } else if (map[i][j] == 3) {

                } else if (map[i][j] == 4) {

                } else if (map[i][j] == 5) {

                }
            }
        }
    }
}
