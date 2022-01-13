/******************************************************************************

 문제출처 : https://programmers.co.kr/learn/courses/30/lessons/43162
 풀이시간 : 10m
 시간복잡도 : O(n^2) 
 공간복잡도 : O(n)
 
 *******************************************************************************/

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (isVisited[i]) continue;

            dfs(computers, i, isVisited);
            answer++;
        }

        return answer;
    }

    private void dfs(int[][] computers, int cur, boolean[] isVisited) {
        isVisited[cur] = true;

        for (int adj = 0; adj < computers.length; adj++) {
            if (cur == adj || computers[cur][adj] != 1 || isVisited[adj]) continue;
            dfs(computers, adj, isVisited);
        }

    }
}
