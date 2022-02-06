import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    private static final int[] moveHeight = {-1, 0, 0, 1};
    private static final int[] moveWidth = {0, -1, 1, 0};
    private static int height;
    private static int width;
    private static int[][] map;
    private static int[][] visited;
    private static int answer = 0;
    private static int quantity = 1;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        map = new int[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        while (quantity > 0 && quantity < 2) {
            runTime();

            visited = new int[height][width];
            quantity = 1;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] > 0 && visited[i][j] == 0) {
                        bfs(i, j);
                        quantity++;
                    }
                }
            }
            quantity--;
        }

        if (quantity == 0) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }

    public static void runTime() {
        answer++;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] > 0) {
                    int count = 0;
                    for (int location = 0; location < 4; location++) {
                        int nextHeight = i + moveHeight[location];
                        int nextWidth = j + moveWidth[location];
                        if (map[nextHeight][nextWidth] == 0) {
                            count++;
                        }
                    }
                    map[i][j] = map[i][j] - count;
                }
            }
        }
    }

    public static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{i, j});
        visited[i][j] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nowHeight = poll[0];
            int nowWidth = poll[1];

            for (int location = 0; location < 4; location++) {
                int nextHeight = nowHeight + moveHeight[location];
                int nextWidth = nowWidth + moveWidth[location];
                if (map[nextHeight][nextWidth] > 0 && visited[nextHeight][nextWidth] == 0) {
                    queue.add(new int[]{nextHeight, nextWidth});
                    visited[nextHeight][nextWidth] = 1;
                }
            }
        }
    }
}
