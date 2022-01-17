import java.io.*;
public class BOJ9251 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	    char[] arr1 = br.readLine().toCharArray();
	    char[] arr2 = br.readLine().toCharArray();
	    
	    int N = arr1.length;
	    int M = arr2.length;
	    
	    int[][] dp = new int[N + 1][M + 1];
	    int answer = 0;
	    for (int n=0;n<N;n++) {
	        for (int m=0;m<M;m++) {
	            if (arr1[n] == arr2[m]) {
	                dp[n + 1][m + 1] = Math.max(dp[n][m] + 1, dp[n + 1][m]);
	            } else {
	                dp[n + 1][m + 1] = Math.max(dp[n][m + 1], dp[n + 1][m]);
	            }
	            
	            answer = Math.max(answer, dp[n+1][m+1]);
	        }
	    }
	    
	    System.out.println(answer);
	}
}

/***
 * 소요시간: 20분
 * 시간복잡도: O(NM)
 * 공간복잡도: O(NM)
 */

/**
 *   A C A Y K P 
* C 0 1 1 1 1 1
* A 1 1 2 2 2 2
* P 1 1 2 2 2 3 
* C
* A
* K
*
* 
* 
* if (str1.charAt(i) == str2.charAt(j)
*  dp[n][m] = max(dp[n - 1][m - 1] + 1, dp[n][m - 1])
* else
*  dp[n][m] = max(dp[n][m-1], dp[n - 1][m])
*/