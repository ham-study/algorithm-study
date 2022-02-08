import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int lineQuantity = Integer.parseInt(bufferedReader.readLine());

        ArrayList<int[]> locations = new ArrayList<>();

        for (int i = 0; i < lineQuantity; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int target1 = Integer.parseInt(input[0]);
            int target2 = Integer.parseInt(input[1]);

            locations.add(new int[]{target1, target2});
        }

        Collections.sort(locations, Comparator.comparingInt(x -> x[0]));

        int length = 0;
        int min = 0;
        int max = 0;
        for (int i = 0; i < locations.size(); i++) {
            int[] location = locations.get(i);

            int x = location[0];
            int y = location[1];

            if (min == 0 && max == 0) {
                min = x;
                max = y;
                length = y - x;
                continue;
            }

            if ((min <= x && x <= max) && (min <= y && y <= max)) { //이미 체크한 범위 안에 라인이 겹쳐 그려질 경우
                continue;
            }

            if ((min <= x && x <= max) && max < y) { // y값이 max보다 큰 경우
                length += (y - max);
                max = y;
                continue;
            }

            if (max < x) {  //x값이 max 값보다 큰 경우
                length += (y - x);
                min = x;
                max = y;
            }
        }

        System.out.println(length);
    }
}
