import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int childQuantity = Integer.parseInt(input[0]);
        int groupQuantity = Integer.parseInt(input[1]);

        int[] children = Arrays.stream(bufferedReader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        //차이 구하기
        int[] dist = new int[childQuantity - 1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = children[i + 1] - children[i];
        }

        int[] sortedArray = Arrays.stream(dist)
            .sorted()
            .distinct()
            .toArray();

        int count = 0;
        int answer = 0;
        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if(count == groupQuantity){
                    System.out.println(answer);
                    return;
                }
                if (sortedArray[i] == dist[j]) {
                    int leftIdx = j;
                    int rightIdx = j+1;
                    answer += dist[j];
                    dist[leftIdx] = -1;
                    dist[rightIdx] = -1;
                    dist[j] = 0;
                }
            }
        }
    }
}
