/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/6443
풀이시간 : 1h
시간복잡도 : O(n^n) (n: 입력 길이) 맞나? - T(n) = nT(n-1) + C
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static char[] inputs;
    private static char[] charCounts;
    private static StringBuilder answer;

	public static void main(String[] args) throws IOException{
        var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int tcNum = Integer.parseInt(br.readLine());
	    
	    while(tcNum-- > 0){
	        solve(br.readLine());
	    }
	    
	    br.close();
	}
	
	private static void solve(String str){
	    inputs = str.toCharArray();
	    charCounts = new char[26];
	    answer = new StringBuilder();
	    
	    for(char input : inputs){
	        charCounts[input - 'a']++;
	    }
	    
	    backtrack(0, new char[inputs.length]);
	    
	   System.out.print(answer.toString());
	}
	
	private static void backtrack(int selectedCnt, char[] selectedCh){
	    if(selectedCnt == inputs.length){
	        answer.append(selectedCh).append('\n');
	        return;
	    }
	    
	    for(int alphaCode = 0; alphaCode < 26; alphaCode++){
	        if(charCounts[alphaCode] == 0) continue;
	        
	        charCounts[alphaCode]--;
	        selectedCh[selectedCnt] = (char)(alphaCode + 'a');
	        backtrack(selectedCnt + 1, selectedCh);
	        charCounts[alphaCode]++;
	    }
	}
}
