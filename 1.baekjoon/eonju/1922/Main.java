import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {

    private static int computerQuantity;
    private static int edgeQuantity;
    private static List<Edge> edges;
    private static int[] parent;
    private static int cnt;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        computerQuantity = Integer.parseInt(bufferedReader.readLine());
        edgeQuantity = Integer.parseInt(bufferedReader.readLine());

        edges = new ArrayList<>();
        parent = new int[computerQuantity + 1];

        for (int i = 1; i <= computerQuantity; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edgeQuantity; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int computerA = Integer.parseInt(input[0]);
            int computerB = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            edges.add(new Edge(computerA, computerB, weight));
        }

        edges.sort(Comparator.comparingInt(Edge::getWeight));

        for (int i = 0; i < edgeQuantity; i++) {
            Edge edge = edges.get(i);
            if (checkCycle(edge)) {
                answer += edge.getWeight();
                cnt++;
            }

            if (cnt == computerQuantity - 1) {
                System.out.println(answer);
                break;
            }
        }
    }

    public static boolean checkCycle(Edge edge) {
        int computerA = edge.getStart();
        int computerB = edge.getEnd();
        if (parent[computerA] != parent[computerB]) {
            parent[computerB] = parent[computerA];
            return true;
        }
        return false;
    }

    static class Edge {

        private int start;
        private int end;
        private int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}
