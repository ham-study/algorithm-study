import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int min = Integer.MAX_VALUE;
    static int subin, sister;
    static boolean[] visited;
    static int max = 100000;

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        subin = scan.nextInt();
        sister = scan.nextInt();

        visited = new boolean[max + 1];
        bfs();
        System.out.println(min);
    }

    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(subin, 0));

        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            visited[pollNode.getLocation()] = true;

            if (pollNode.location == sister) {
                min = Math.min(min, pollNode.getTime());
            }

            if (pollNode.location * 2 <= max && !visited[pollNode.location * 2]) {
                queue.offer(new Node(pollNode.getLocation() * 2, pollNode.getTime()));
            }
            if (pollNode.location + 1 <= max && !visited[pollNode.location + 1]) {
                queue.offer(new Node(pollNode.getLocation() + 1, pollNode.getTime() + 1));
            }
            if (pollNode.location - 1 >= 0 && !visited[pollNode.location - 1]) {
                queue.offer(new Node(pollNode.getLocation() - 1, pollNode.getTime() + 1));
            }
        }
    }

    public static class Node {

        private int location;
        private int time;

        public Node(int x, int time) {
            this.location = x;
            this.time = time;
        }

        public int getLocation() {
            return location;
        }

        public int getTime() {
            return time;
        }
    }
}
