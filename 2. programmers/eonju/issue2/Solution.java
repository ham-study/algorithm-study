package issue2;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

class Solution {

    private final static int hit = 1;
    private final static int miss = 5;

    public static int solution(int cacheSize, String[] cities) {
        List<String> cache = new LinkedList<>();
        int time = 0;

        if (cacheSize == 0) {
            return cities.length * miss;
        }

        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase(Locale.ROOT);
        }

        for (int i = 0; i < cities.length; i++) {
            if (cache.isEmpty()) {
                cache.add(cities[i]);
                time += miss;
                continue;
            }
            if (cache.contains(cities[i])) {
                int target = cache.indexOf(cities[i]);
                cache.remove(target);
                cache.add(cities[i]);
                time += hit;
                continue;
            }
            if (!cache.contains(cities[i]) && cache.size() < cacheSize) {
                cache.add(cities[i]);
                time += miss;
                continue;
            }
            if (!cache.contains(cities[i]) && cache.size() == cacheSize) {
                String firstIndex = findFirstIndex(cache);
                cache.remove(firstIndex);
                cache.add(cities[i]);
                time += miss;
            }
        }

        return time;
    }

    public static String findFirstIndex(List<String> cache) {
        return cache.stream().findFirst().orElse("");
    }
}
