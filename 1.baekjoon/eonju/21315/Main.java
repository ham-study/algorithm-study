import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int n;
    private static Queue<Integer> cards;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        Queue<Integer> target = new LinkedList<>();
        String[] input = bufferedReader.readLine().split(" ");
        for (String s : input) {
            target.add(Integer.parseInt(s));
        }

        int number = 1;
        ArrayList<Integer> candidate = new ArrayList<>();
        while ((int) Math.pow(2, number) <= n) {
            candidate.add(number);
            number++;
        }

        cards = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            cards.add(i);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < candidate.size(); i++) {
            Queue<Integer> mixed = mix(candidate.get(i));
            if (target.equals(mixed)) {
                answer.append(i).append(" ");
            }
        }

        System.out.println(answer);
    }

    public static Queue<Integer> mix(int target) {
        Queue<Integer> mixed = new LinkedList<>(cards);
        Queue<Integer> temp = new LinkedList<>();

        int upCnt1;
        int upCnt2;
        for (int i = 0; i < 2; i++) {
            int level = 1;
            upCnt1 = (int) Math.pow(2, target);
            mix(upCnt1, mixed);
            level++;

            while (level <= target + 1) {
                temp.clear();
                for (int j = 0; j < upCnt1; j++) {
                    Integer poll = mixed.poll();
                    temp.add(poll);
                }

                upCnt2 = (int) Math.pow(2, target - level + 1);
                mix(upCnt2, temp);

                while (mixed.size() != 0) {
                    Integer poll = mixed.poll();
                    temp.add(poll);
                }

                mixed.addAll(temp);

                upCnt1 = upCnt2;
                level++;
            }
        }

        return mixed;
    }

    public static void mix(int count, Queue<Integer> queue) {
        for (int i = 0; i < queue.size() - count; i++) {
            Integer poll = queue.poll();
            queue.add(poll);
        }
    }

}
