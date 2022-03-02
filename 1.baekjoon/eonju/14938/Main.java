import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Main {

    private static int city;
    private static int seekBound;
    private static int road;
    private static int[] items;
    private static int[] dist;
    private static Map<Integer, ArrayList<Edge>> graph;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        city = Integer.parseInt(input[0]);
        seekBound = Integer.parseInt(input[1]);
        road = Integer.parseInt(input[2]);
        items = new int[city + 1];

        input = bufferedReader.readLine().split(" ");
        graph = new HashMap<>();
        for (int i = 1; i <= city; i++) {
            items[i] = Integer.parseInt(input[i - 1]);
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < road; i++) {
            input = bufferedReader.readLine().split(" ");

            int cityA = Integer.parseInt(input[0]);
            int cityB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            graph.get(cityA).add(new Edge(cityB, weight));
            graph.get(cityB).add(new Edge(cityA, weight));
        }

        for (int i = 1; i <= city; i++) {
            dijkstra(i);

            int sum = 0;
            for (int j = 1; j <= city; j++) {
                if (dist[j] <= seekBound) {
                    sum += items[j];
                }
            }

            if (sum > answer) {
                answer = sum;
            }
        }

        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        dist = new int[city + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        queue.add(new Edge(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            if (poll.getWeight() > dist[poll.getEnd()]) {
                continue;
            }

            ArrayList<Edge> edges = graph.get(poll.getEnd());
            for (Edge edge : edges) {
                if (poll.getWeight() + edge.getWeight() < dist[edge.getEnd()]) {
                    dist[edge.getEnd()] = poll.getWeight() + edge.getWeight();
                    queue.add(new Edge(edge.getEnd(), dist[edge.getEnd()]));
                }
            }
        }
    }

    static class Edge {

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
}
