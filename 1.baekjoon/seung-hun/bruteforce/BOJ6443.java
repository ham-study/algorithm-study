import java.io.*;
import java.util.*;

public class BOJ6443 {
    private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
		    Map<Character, Integer> count = new HashMap<>();
		    String line = br.readLine();
		    countChars(count, line);
		    List<Character> list = new ArrayList<>(count.keySet());
		    Collections.sort(list);
		    
		    dfs(list, count, new ArrayList<>(), line.length());
		}
		System.out.println(sb);
	}
	 
	private static void dfs(List<Character> list, Map<Character, Integer> count, List<Character> result, int limit) {
	    if (result.size() == limit) {
	        
	        for (char ch : result) {
	            sb.append(ch);
	        }
	        sb.append("\n");
	        return;
	    }
	    
	    for (int i=0;i<list.size();i++) {
	        if (count.get(list.get(i)) == 0) continue;
	        count.compute(list.get(i), (k, v) -> v - 1);
	        result.add(list.get(i));
	        dfs(list, count, result, limit);
	        result.remove(result.size() - 1);
	        count.compute(list.get(i), (k, v) -> v + 1);
	    }
	}
	private static void countChars(Map<Character, Integer> count, String str) {
	    for (char ch : str.toCharArray()) {
	        count.putIfAbsent(ch, 0);
	        count.compute(ch, (k, v) -> v + 1);
	    }
	}
}
/**
 * 소요시간: 10분
 * 시간복잡도: O(M!)
 * 공간복잠도: O(M!)(?) 함수 호출 스택 고려?
 */
