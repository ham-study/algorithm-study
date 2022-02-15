package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int nodeQuantity = Integer.parseInt(input[0]);
        int roadQuantity = Integer.parseInt(input[1]);

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < roadQuantity; i++) {
            input = bufferedReader.readLine().split(" ");
            int target1 = Integer.parseInt(input[0]);
            int target2 = Integer.parseInt(input[1]);

            map.putIfAbsent(target1, new ArrayList<>());
            map.putIfAbsent(target2, new ArrayList<>());

            map.get(target1).add(target2);
            map.get(target2).add(target1);
        }

        for (Integer key : map.keySet()) {

        }

    }

}
