import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] info = bufferedReader.readLine().split(" ");
        int houseQuantity = Integer.parseInt(info[0]);
        int routerQuantity = Integer.parseInt(info[1]);

        int[] positions = new int[houseQuantity];

        for (int i = 0; i < houseQuantity; i++) {
            int position = Integer.parseInt(bufferedReader.readLine());
            positions[i] = position;
        }

        Arrays.sort(positions);

        int min = 0;
        int max = 0;

        for (int i = 1; i < positions.length; i++) {
            int targetPosition = positions[i];
            int prePosition = positions[i - 1];

            if (targetPosition - prePosition < min) {
                min = targetPosition - prePosition;
            }
            if (max < targetPosition - prePosition) {
                max = targetPosition - prePosition;
            }
        }


    }

    public static void binarySearch(int[] positions, int min, int max) {

    }
}
