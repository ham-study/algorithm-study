import java.io.*;
import java.util.*;

public class BOJ13549 {
    static class Point {
        int x;
        int time;
        
        Point(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
    static final int INF = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, K));
        
    }
    private static int bfs(int n, int k) {
        LinkedList<Point> deque = new LinkedList<>(); 
        boolean[] visited = new boolean[INF];
        deque.add(new Point(n, 0));
        visited[n] = true;

        while (!deque.isEmpty()) {
            Point current = deque.removeFirst();

            if (current.x == k)
                return current.time;

            if (current.x * 2 < INF && !visited[current.x * 2]) {
                visited[current.x * 2] = true;
                deque.addFirst(new Point(current.x * 2, current.time));
            }

            if (current.x + 1 < INF && !visited[current.x + 1]) {
                visited[current.x + 1] = true;
                deque.addLast(new Point(current.x + 1, current.time + 1));
            }

            
            if (current.x - 1 >= 0 && !visited[current.x - 1]) {
                visited[current.x - 1] = true;
                deque.addLast(new Point(current.x - 1, current.time + 1));
            }
        }
        return -1;
    }
}
/**
 * 소요시간: 25분
 * 시간복잡도: O(N-K)
 * 공간복잡도: O(N-K)
 */