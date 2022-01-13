import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] visited;
    private static int count = 0;

    public static int solution(int n, int[][] computers) {
        visited = new int[n];

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    map.get(i).add(j);
                }
            }
        }

        for (int i = 0; i < map.size(); i++) {
            if (!map.get(i).isEmpty() && visited[i] == 0) {
                dfs(i);
                count++;
            } else if (map.get(i).isEmpty()) {
                count++;
            }
        }

        return count;
    }

    public static void dfs(int i) {
        visited[i] = 1;
        for (Integer linked : map.get(i)) {
            if (visited[linked] == 0) {
                dfs(linked);
            }
        }
    }
}
