import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    private static final int[] moveHeight = {0, 0, -1, 1};
    private static final int[] moveWidth = {1, -1, 0, 0};
    private static int[][] map;
    private static int[][] visited;
    private static int height;
    private static int width;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        map = new int[height][width];

        for (int i = 0; i < height; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        visited = new int[height][width];
        bfs(0, 0);
        if (visited[height - 1][width - 1] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(visited[height - 1][width - 1]);
        }
    }

    public static void bfs(int startI, int startJ) {
        LinkedList<int[]> queue = new LinkedList<>();
        visited[startI][startJ] = 1;
        queue.add(new int[]{startI, startJ, 1, 0}); // indexI, indexJ, count, 벽 부순 여부

        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            int nowIndexI = target[0];
            int nowIndexJ = target[1];
            int nowCount = target[2];
            int breakWallCount = target[3];

            for (int i = 0; i < 4; i++) {
                int nextI = nowIndexI + moveHeight[i];
                int nextJ = nowIndexJ + moveWidth[i];
                int nextCount = nowCount + 1;

                if (!isCorrectRange(nextI, nextJ)) {
                    continue;
                }
                if (isVisited(nextI, nextJ)) {
                    continue;
                }
                if (map[nextI][nextJ] == 0) {
                    queue.add(new int[]{nextI, nextJ, nextCount, breakWallCount});
                    visited[nextI][nextJ] = nextCount;
                    continue;
                }
                if (map[nextI][nextJ] == 1 && breakWallCount == 0) {
                    queue.add(new int[]{nextI, nextJ, nextCount, breakWallCount + 1});
                    visited[nextI][nextJ] = nextCount;
                }
            }

        }
    }

    public static boolean isCorrectRange(int indexI, int indexJ) {
        return indexI >= 0 && indexJ >= 0 && indexI < height && indexJ < width;
    }

    public static boolean isVisited(int indexI, int indexJ) {
        return visited[indexI][indexJ] > 0;
    }
}
