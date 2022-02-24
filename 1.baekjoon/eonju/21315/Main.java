import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int n;
    private static Queue<Integer> cards;
    private static Queue<Integer> target;
    private static Queue<Integer> mixed;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        target = new LinkedList<>();
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
        for (int k1 = 0; k1 < candidate.size(); k1++) {
            for (int k2 = 0; k2 < candidate.size(); k2++) {
                mixed = new LinkedList<>(cards);

                mix(candidate.get(k1));
                mix(candidate.get(k2));

                if (target.equals(mixed)) {
                    answer.append(candidate.get(k1)).append(" ").append(candidate.get(k2));
                }
            }
        }

        System.out.println(answer);
    }

    public static void mix(int k) {
        Queue<Integer> temp = new LinkedList<>();

        int upCnt1; // 이전에 올린 카드 갯수
        int upCnt2; // 현재 올릴 카드 갯수
        int level = 1;

        upCnt1 = (int) Math.pow(2, k);
        mix(upCnt1, mixed);
        level++;

        while (level <= k + 1) {
            temp.clear();
            for (int j = 0; j < upCnt1; j++) {
                Integer poll = mixed.poll();
                temp.add(poll);
            }

            upCnt2 = (int) Math.pow(2, k - level + 1);
            mix(upCnt2, temp);

            while (!mixed.isEmpty()) {
                Integer poll = mixed.poll();
                temp.add(poll);
            }

            mixed.addAll(temp);

            upCnt1 = upCnt2;
            level++;
        }
    }

    public static void mix(int count, Queue<Integer> queue) {
        for (int i = 0; i < queue.size() - count; i++) {
            Integer poll = queue.poll();
            queue.add(poll);
        }
    }
}
