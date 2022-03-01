import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {

    private static int houseQuantity;
    private static int roadQuantity;
    private static HashMap<Integer, ArrayList<Edge>> nodes;
    private static boolean[] visited;
    private static int maxWeight;
    private static int sumWeight;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");

        houseQuantity = Integer.parseInt(input[0]);
        roadQuantity = Integer.parseInt(input[1]);

        nodes = new HashMap<>();
        for (int i = 1; i <= houseQuantity; i++) {
            nodes.put(i, new ArrayList<>());
        }

        for (int i = 0; i < roadQuantity; i++) {
            input = bufferedReader.readLine().split(" ");
            int houseA = Integer.parseInt(input[0]);
            int houseB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            nodes.get(houseA).add(new Edge(houseB, weight));
            nodes.get(houseB).add(new Edge(houseA, weight));
        }

        visited = new boolean[houseQuantity + 1];
        maxWeight = 0;
        sumWeight = 0;

        prim(1);

        System.out.println(sumWeight - maxWeight);
    }

    public static void prim(int start) {
        Queue<Integer> nodeQ = new LinkedList<>();
        PriorityQueue<Edge> edgeQ = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        nodeQ.add(start);

        while (!nodeQ.isEmpty()) {
            int nowNode = nodeQ.poll();
            visited[nowNode] = true;
            List<Edge> edges = nodes.get(nowNode);

            for (Edge edge : edges) {
                if (!visited[edge.end]) {
                    edgeQ.add(edge);
                }
            }

            while (!edgeQ.isEmpty()) {
                Edge poll = edgeQ.poll();
                if (!visited[poll.end]) {
                    visited[poll.end] = true;
                    sumWeight += poll.weight;
                    maxWeight = Math.max(poll.weight, maxWeight);
                    nodeQ.add(poll.end);
                    break;
                }
            }
        }
    }

    static class Edge {

        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
