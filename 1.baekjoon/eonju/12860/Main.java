import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int productQuantity = Integer.parseInt(input[0]);
        int targetWeight = Integer.parseInt(input[1]);

        ArrayList<int[]> backpack = new ArrayList<>();  // int[0] = 무게 , int[1] = 가치
        for (int i = 0; i < productQuantity; i++) {
            input = bufferedReader.readLine().split(" ");
            backpack.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
        }

        backpack.sort(Comparator.comparingInt(x -> x[0]));

        int maxValue = 0;

        for (int i = 0; i < backpack.size(); i++) {
            int[] targetProduct = backpack.get(i);

            int sumWeight = targetProduct[0];
            int sumValue = targetProduct[1];
            int idx = i + 1;

            while (sumWeight < targetWeight && idx < backpack.size()) {
                int[] addProduct = backpack.get(idx);

                if (sumWeight + addProduct[0] <= targetWeight) {
                    sumWeight += addProduct[0];
                    sumValue += addProduct[1];
                    idx++;
                    continue;
                }

                if (sumWeight + addProduct[0] > targetWeight) {
                    idx++;
                }
            }

            if (maxValue < sumValue) {
                maxValue = sumValue;
            }
        }

        System.out.println(maxValue);

    }
}

/*
 * 무게 순으로 sorting 후 순서대로 하나씩 더하며 무게를 비교한뒤
 *
 *
 * */
