import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

    private static HashMap<Integer, List<Integer>> nodes;
    private static int nodeQuantity;
    private static int edgeQuantity;
    private static int shortest;
    private static int startNode;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] info = bufferedReader.readLine().split(" ");
        nodeQuantity = Integer.parseInt(info[0]);
        edgeQuantity = Integer.parseInt(info[1]);
        shortest = Integer.parseInt(info[2]);
        startNode = Integer.parseInt(info[3]);

        nodes = new HashMap<>();

        for (int i = 0; i < edgeQuantity; i++) {
            String[] targets = bufferedReader.readLine().split(" ");
            int target1 = Integer.parseInt(targets[0]);
            int target2 = Integer.parseInt(targets[1]);

            nodes.putIfAbsent(target1, new ArrayList<>());
            nodes.putIfAbsent(target2, new ArrayList<>());

            nodes.get(target1).add(target2);
            nodes.get(target2).add(target1);
        }

        bfs();
    }

    public static void bfs() {
        int[] visited = new int[nodeQuantity + 1];
        Queue<Integer> queue = new LinkedList<>();
        int count = 1;

        queue.add(startNode);

        while (!queue.isEmpty()) {
            Integer pollNode = queue.poll();
            visited[pollNode] = 1;

            if (count == shortest) {
                break;
            }

            for (Integer linkedNode : nodes.get(pollNode)) {
                if (visited[linkedNode] == 0) {
                    queue.add(linkedNode);
                }
            }
            count++;
        }

        if (queue.isEmpty()) {
            System.out.println(-1);
        } else {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
        }
    }
}
