/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/2110
풀이시간 : 2h
시간복잡도 : O(nlogn) = O(200,000 * 5.3) =~ O(1,000,000), (n = houseNum <= 200,000)
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static int houseNum, wifiNum;
    private static int startHouseNum, endHouseNum;
    private static int[] houses;
    
	public static void main(String[] args) throws Exception{
	    setInitialValue();
	    
	    int answer = binarySearch();
	    
	    System.out.print(answer);
	}
	
	private static int binarySearch(){ // O(log(endHouseNum - startHouseNum)) 
	    // upper bound
      int minLen = 1;
	    int maxLen = endHouseNum - startHouseNum + 1;
	    int midLen = 0;
	    
	    while(minLen < maxLen){
	        midLen = (minLen + maxLen) / 2;
	        
	        if(isSettableForLen(midLen)){
	            minLen = midLen + 1;
	        }else{
	            maxLen = midLen;
	        }
	    }
	    
	    return midLen - 1;
	}
	
	private static boolean isSettableForLen(int minDistance){
	    int prevHouseIdx = 0;
	    int setCount = 1;
	    
	    for(int curHouseIdx = 1; curHouseIdx < houses.length; curHouseIdx++){
	        int distance = houses[curHouseIdx] - houses[prevHouseIdx];
	        
	        if(minDistance <= distance){
	            prevHouseIdx = curHouseIdx;
	            setCount++;
	        }
	    }
	    
	    return setCount >= wifiNum;
	}
	
	private static void setInitialValue() throws Exception{
	    var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    var st = new StringTokenizer(br.readLine());
	    
	    houseNum = Integer.parseInt(st.nextToken());
	    wifiNum = Integer.parseInt(st.nextToken());
	    
	    houses = new int[houseNum + 1];

	    for(int i = 1; i <= houseNum; i++){
	        houses[i] = Integer.parseInt(br.readLine());
	    }
	    
	    Arrays.sort(houses); // O(houseNum * log(houseNum)), houseNum <= 200,000
	    
	    startHouseNum = houses[1];
	    endHouseNum = houses[houses.length - 1];
	    
	    br.close();
	}
	
}
