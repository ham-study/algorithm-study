import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static Map<Integer, List<Edge>> graph;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int nodeQuantity = info[0];
        int edgeQuantity = info[1];

        graph = new HashMap<>();

        for (int i = 0; i < edgeQuantity; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int node1 = Integer.parseInt(input[0]);
            int node2 = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            graph.putIfAbsent(node1, new ArrayList<>());
            graph.putIfAbsent(node2, new ArrayList<>());

            graph.get(node1).add(new Edge(node2, weight));
            graph.get(node2).add(new Edge(node1, weight));
        }

        String[] input = bufferedReader.readLine().split(" ");
        int mustPass1 = Integer.parseInt(input[0]);
        int mustPass2 = Integer.parseInt(input[1]);

        distance = new int[nodeQuantity + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        dijkstra(1);

        System.out.println(distance[nodeQuantity - 1]);
    }

    public static void dijkstra(int startNode) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        queue.add(new Edge(startNode, 0));
        distance[startNode] = 0;

        while (!queue.isEmpty()) {
            Edge target = queue.poll();

            if (target.getWeight() > distance[target.getEnd()]) {
                continue;
            }

            List<Edge> linkedEdges = graph.get(target.getEnd());

            if (linkedEdges != null) {
                for (Edge edge : linkedEdges) {
                    int nextNode = edge.getEnd();
                    if (target.getWeight() + edge.getWeight() < distance[nextNode]) {
                        distance[nextNode] = target.getWeight() + edge.getWeight();
                        queue.add(new Edge(nextNode, distance[nextNode]));
                    }
                }
            }
        }
    }
}

class Edge {

    private int end;
    private int weight;

    public Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}
