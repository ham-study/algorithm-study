import java.util.*;

class Solution {
    private final int MISS_TIME = 5;
    private final int HIT_TIME = 1;

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * MISS_TIME;

        LinkedHashSet<String> cache = new LinkedHashSet<>(cacheSize);

        int time = 0;

        for (var citylow : cities) {
            String city = citylow.toUpperCase();

            if (cache.contains(city)) {  // hit일 경우
                cache.remove(city);
                cache.add(city);

                time += HIT_TIME;
                continue;
            }

            if (cache.size() < cacheSize) {
                cache.add(city);

                time += MISS_TIME;
                continue;
            }

            String first = this.findFirst(cache);
            cache.remove(first);
            cache.add(city);

            time += MISS_TIME;
        }

        return time;
    }

    private String findFirst(LinkedHashSet<String> cache) {
        for(var val : cache) return val;

        throw new RuntimeException("예외");
    }

    public static void main(String[] args) {
        var sol = new Solution();

//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        var solution = sol.solution(0, cities);
        System.out.println(solution);
    }
}
