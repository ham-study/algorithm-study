import java.util.*;

class Solution {
    private static final int HIT = 1;
    private static final int MISS = 5;
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();
        
        for (String city : cities) {
            city = city.toUpperCase();
            
            if (cache.contains(city)) {
                cache.remove(city);
                cache.addLast(city);
                answer += HIT;
            } else {
                cache.addLast(city);
                
                if (cache.size() > cacheSize) {
                    cache.removeFirst();
                }
                
                answer += MISS;
                
            }
        }
        return answer;
    }

}

/**
 * 소요시간: 15분
 * 시간복잡도: O(NM)
 * 공간복잡도: O(M)
 */