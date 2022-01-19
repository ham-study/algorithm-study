import java.util.*;
import java.io.*;

public class BOJ2110 {
	
	private static int N, C;
	private static int[] arr;
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    C = Integer.parseInt(st.nextToken());
	    
	    arr = new int[N];
	    
	    for (int i=0;i<N;i++) {
	        arr[i] = Integer.parseInt(br.readLine());
	    }
	    Arrays.sort(arr);
	    System.out.println(solve());
	}
	
	private static int solve() {
	    int left = 0;
	    int right = arr[N - 1] + 1;
	    int answer = 0;
	    while (left + 1 < right) {
	        int mid = left + (right - left) / 2;   
	    
	        if (check(mid)) {
	            left = mid;
	            answer = Math.max(answer, mid);
	        } else {
	            right = mid;
	        }
	    }
	    
	    return answer;
	}
	
	private static boolean check(int limit) {
	    int count = 1;
	    int current = arr[0];
	    for (int i=1;i<N;i++) {
	        int diff = arr[i] - current;
	        if (diff >= limit) {
	            count++;
	            current = arr[i];
	        }
	    }
	    
	    return count >= C;
	}
}
/**
 * 소요시간: 1H+
 * 시간복잡도: (Nlog(10억))
 * 공간복잡도: O(1)
/

/**
 * 1. 이분 탐색으로 가장 인접한 두 노드 결정
 * 2. left mid right
 * - left - mid가 가장 인접한 경우 > mid - right 사이에서 mid - left 보다 작은 값 나오면 안됨
 * - mid - right가 가장 인접한 경우 > left - mid 사이에서 right - mide 보다 작은 값 나오면 안됨
 * 3. count를 세서 C - 2보다 크거나 같으면 OK
 * 4. left subarray가 성공하면 left = mid
 * 5. right subarray가 성공하면 right - mid
1 2 4 8 9
*/