import java.io.*;
public class BOJ15886 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        String moves = br.readLine().replace("EW", "T");
        for (int i=0;i<moves.length();i++) {
            if (moves.charAt(i) == 'T') {
                answer++;
            }
        }
        System.out.println(answer);
    }
    
}
/***
소요시간: 1H
시간복잡도: O(N)
공간복잡도: O(N);
/
/**
E E W W E W
1 1 1
  1 1
  1 1
  1 1 1
        1 1
        1 1
1 4 4 1 2 2

반례
EW
EWW

ETWT
EEEWW
T
TW

EW로 어쨌든 모인다-> EW 개수 구하면됨
*/