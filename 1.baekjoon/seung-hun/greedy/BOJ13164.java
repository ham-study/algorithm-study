import java.io.*;
import java.util.*;
public class BOJ13164 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] children = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            children[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] diffs = getDiffsOrderByAsc(children);
        int answer = getSum(diffs, 0, n - k);
        
        System.out.println(answer);
    }
    
    private static int[] getDiffsOrderByAsc(int[] array) {
        int length = array.length - 1;
        int[] result = new int[length];
        
        for (int i=0;i<length;i++) {
            result[i] = array[i + 1] - array[i];
        }
        
        Arrays.sort(result);
        
        return result;
    }
    
    private static int getSum(int[] array, int start, int end) {
        int sum = 0;
        
        for (int i=start;i<end;i++) {
            sum += array[i];
        }
        
        return sum;
    }
}
/***
 * 소요시간: 1H+
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */

 /**
1. 비용을 먼저 정한다 (X) -> 비용이 균일하지 않음. 파라메트릭서치 쓰기 어려울 듯
1 3 4 6 10
2 1 2 4
1 2 2 4

1 3 6 10 15 21
2 3 4 5 6
*/