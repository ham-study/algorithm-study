import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int answer = 0;
    static int[][] map;
    static final int[] moveI = {-1, 1, 0, 0};
    static final int[] moveJ = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        while (true) {
            int count = 0;
            int melt[][] = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            if (count > 1) {
                break;
            } else if (count == 0) {
                answer = 0;
                break;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) {
                        for (int location = 0; location < 4; location++) {
                            int nextI = i + moveI[location];
                            int nextJ = j + moveJ[location];
                            if (nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
                                continue;
                            }
                            if (map[nextI][nextJ] < 1) {
                                melt[i][j]++;
                            }
                        }
                    }

                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] -= melt[i][j];
                }
            }

            answer++;
        }

        System.out.println(answer);

    }

    public static void bfs(int i, int j) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(i, j));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Position poll = queue.poll();

            for (int location = 0; location < 4; location++) {
                int nextI = poll.i + moveI[location];
                int nextJ = poll.j + moveJ[location];

                if (nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
                    continue;
                }

                if (!visited[nextI][nextJ] && map[nextI][nextJ] > 0) {
                    queue.add(new Position(nextI, nextJ));
                    visited[nextI][nextJ] = true;
                }
            }
        }
    }

    static class Position {

        int i;
        int j;

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
