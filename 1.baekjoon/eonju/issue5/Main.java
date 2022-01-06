import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    private static final int NODE_QUANTITY = 0;
    private static final int EDGE_QUANTITY = 1;
    private static final int START_NODE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Node> nodes = new HashMap<>();

        int[] info = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        for (int i = 0; i < info[NODE_QUANTITY]; i++) {
            nodes.put(i + 1, new Node(i + 1));
        }

        for (int i = 0; i < info[EDGE_QUANTITY]; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            Node firstNode = nodes.get(edge[0]);
            Node secondNode = nodes.get(edge[1]);

            firstNode.addLinkedNode(edge[0]);
            secondNode.addLinkedNode(edge[1]);
        }

        String dfs_result = dfs(nodes, info[EDGE_QUANTITY], info[START_NODE]);
        String bfs_result = bfs(nodes, info[EDGE_QUANTITY], info[START_NODE]);

        System.out.println(dfs_result);
        System.out.println(bfs_result);
    }

    public static String dfs(Map<Integer, Node> nodes, int edgeSize, int startNode) {
        ArrayList<String> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            Integer target = stack.pop();
            visited.add(String.valueOf(target));

            List<Integer> linked = nodes.get(target).getLinked();
            linked.sort(Comparator.reverseOrder());
            List<Integer> filtered = linked.stream()
                .filter(x -> !visited.contains(x))
                .collect(Collectors.toList());
            if (filtered.isEmpty()){
                break;
            }
            stack.addAll(filtered);
        }

        return String.join(" ", visited);
    }

    public static String bfs(Map<Integer, Node> nodes, int edgeSize, int startNode) {
        ArrayList<String> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startNode);

        while (!queue.isEmpty()) {
            Integer target = queue.poll();
            visited.add(String.valueOf(target));

            List<Integer> linked = nodes.get(target).getLinked();
            linked.sort(Comparator.naturalOrder());
            List<Integer> filtered = linked.stream()
                .filter(x -> !visited.contains(x))
                .collect(Collectors.toList());
            if (filtered.isEmpty()){
                break;
            }
            queue.addAll(filtered);
        }

        return String.join(" ", visited);
    }
}
/**
 * 시간: 1시간 7분 (NumberFormat, 런타임 에러)
 * 시간 복잡도: N²
 * 공간 복잡도: -
 */
