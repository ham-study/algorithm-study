import java.io.*;
import java.util.*;
public class BOJ12865 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] dp = new int[K + 1];
        
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            for (int j=K;j>=w;j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }
        
        System.out.println(dp[K]);
    }
}
/***
 * 소요시간: 5분
 * 시간복잡도: O(N*K)
 * 공간복잡도: O(K)
 */