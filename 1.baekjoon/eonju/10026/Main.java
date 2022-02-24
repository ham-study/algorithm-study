import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    private static final int[] moveI = {-1, 1, 0, 0};
    private static final int[] moveJ = {0, 0, 1, -1};

    private static int N;
    private static String[][] map;
    private static boolean[][] nonBlind_visited;
    private static boolean[][] blind_visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bufferedReader.readLine());

        map = new String[N][N];
        nonBlind_visited = new boolean[N][N];
        blind_visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().split("");
        }

        int blindCnt = 0;
        int nonBlindCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!nonBlind_visited[i][j]) {
                    nonBlind_bfs(i, j);
                    nonBlindCnt++;
                }
                if (!blind_visited[i][j]) {
                    blind_bfs(i, j);
                    blindCnt++;
                }
            }
        }

        System.out.println(nonBlindCnt + " " + blindCnt);
    }

    public static void nonBlind_bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        nonBlind_visited[i][j] = true;
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            for (int location = 0; location < 4; location++) {
                int nextI = target[0] + moveI[location];
                int nextJ = target[1] + moveJ[location];

                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) {
                    continue;
                }

                if (!nonBlind_visited[nextI][nextJ] && map[target[0]][target[1]].equals(map[nextI][nextJ])) {
                    queue.add(new int[]{nextI, nextJ});
                    nonBlind_visited[nextI][nextJ] = true;
                }
            }
        }
    }

    public static void blind_bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        blind_visited[i][j] = true;
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            String now = map[target[0]][target[1]];

            for (int location = 0; location < 4; location++) {
                int nextI = target[0] + moveI[location];
                int nextJ = target[1] + moveJ[location];

                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) {
                    continue;
                }

                if (blind_visited[nextI][nextJ]) {
                    continue;
                }

                if (now.equals("B") && map[nextI][nextJ].equals("B")) {
                    queue.add(new int[]{nextI, nextJ});
                    blind_visited[nextI][nextJ] = true;
                    continue;
                }

                if (!now.equals("B") && !map[nextI][nextJ].equals("B")) {
                    queue.add(new int[]{nextI, nextJ});
                    blind_visited[nextI][nextJ] = true;
                }
            }
        }
    }
}
