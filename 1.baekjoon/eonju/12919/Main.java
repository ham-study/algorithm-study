import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        System.out.println(solution(s, t));
    }

    public static int solution(String s, String t) {
        int numberDigit = t.length() - s.length();
        int tryTimes = (int) Math.pow(2, numberDigit);

        for (int i = 0; i < tryTimes; i++) {
            String binary = makeBinary(i, numberDigit);
            String answer = s;

            for (String number : String.valueOf(binary).split("")) {
                if (number.equals("0")) {
                    answer = addA(answer);
                } else {
                    answer = addB(answer);
                }
            }

            if (answer.equals(t)) {
                return 1;
            }
        }
        return 0;
    }

    public static String addA(String str) {
        return str.concat("A");
    }

    public static String addB(String str) {
        return new StringBuffer(str.concat("B")).reverse().toString();
    }

    public static String makeBinary(int number, int numberDigit) {
        return String.format("%0" + numberDigit + "d", Integer.parseInt(Integer.toBinaryString(number)));
    }
}
/**
 * 시간: 1시간 7분 (NumberFormat, 런타임 에러)
 * 시간 복잡도: N²
 * 공간 복잡도: -
 */
