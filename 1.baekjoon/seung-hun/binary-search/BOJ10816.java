import java.io.*;
import java.util.*;

public class BOJ10816 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];
        Map<Integer, Integer> cache = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i=0;i<N;i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(cards);
        
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<M;i++) {
            int target = Integer.parseInt(st.nextToken());
            
            if (!cache.containsKey(target)) {
                int lower = lowerBound(cards, target);
                int upper = upperBound(cards, target);    
                cache.put(target, upper - lower);
            }
            
            sb.append(cache.get(target)).append(" ");
        }
        
        System.out.println(sb);
    }
    
    private static int lowerBound(int[] cards, int target) {
        int left = -1, right = cards.length;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (cards[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return right;
    }
    
    private static int upperBound(int[] cards, int target) {
        int left = -1, right = cards.length;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (cards[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return right;
    }
}

/**
 * 소요시간: 15분
 * 시간복잡도: O(NlogN + MlogN)
 * 공간복잡도: O(N + M + (cache: N))
 */