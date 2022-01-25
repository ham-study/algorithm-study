/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/18870
 풀이시간 : 1h
 시간복잡도 : O(nlogn) 
 공간복잡도 : O(n)

 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        var solution = new Main();
        solution.solve();
    }

    void solve() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        List<Integer> input = new ArrayList<>();
        Set<Integer> inputSet = new HashSet<>();

        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            var num = Integer.parseInt(st.nextToken());
            inputSet.add(num);
            input.add(num);
        }
        List<Integer> inputSorted = new ArrayList<>(inputSet);
        Collections.sort(inputSorted);

        List<Integer> answer = new ArrayList<>();
        for (var val : input) {
            answer.add(Collections.binarySearch(inputSorted, val));
            //Collections.binarySearch(inputSorted, val);
        }

        answer.stream().forEach(v -> {
            try {
                bw.write(v + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bw.flush();
        bw.close();
    }
}
