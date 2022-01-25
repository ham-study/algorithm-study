import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        int[] locations = Arrays.stream(bufferedReader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] sorted = locations.clone();
        Arrays.sort(sorted);

        int index = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (!map.containsKey(sorted[i])) {
                map.put(sorted[i], index);
                index++;
            }
        }

        String[] answer = new String[n];
        for (int i = 0; i < locations.length; i++) {
            answer[i] = String.valueOf(map.get(locations[i]));
        }
        System.out.println(String.join(" ", answer));
    }
}
