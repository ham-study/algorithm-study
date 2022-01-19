import java.util.*;
class PGMS43162 {
    
    public int solution(int n, int[][] computers) {
        int answer = 0;    
        
        boolean[] visited = new boolean[n];
        
        for (int i=0;i<n;i++) {
            if (visited[i]) continue;
            bfs(i, visited, computers);
            answer++;
        }
        
        return answer;
    }
    
    private static void bfs(int node, boolean[] visited, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        visited[node] = true;
        
        q.add(node);
        
        while (!q.isEmpty()) {
            node = q.poll();
        
            for (int i=0;i<computers.length;i++) {
                if (computers[node][i] == 0) continue;
                if (visited[i]) continue;
                visited[i] = true;
                q.add(i);
            }    
            
        }
    }
}

/***
 * 소요시간: 7분
 * 시간복잡도:O(N)
 * 공간복잡도:O(N) 
 */