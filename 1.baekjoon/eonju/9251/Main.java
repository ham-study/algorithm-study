import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = bufferedReader.readLine().split("");
        String[] s2 = bufferedReader.readLine().split("");

        int answer = 0;

        for (int i = 0; i < s1.length; i++) {
            if (answer >= s1.length - i) {
                break;
            }
            int length = makeSubString(s1, s2, i);
            if (answer < length) {
                answer = length;
            }
        }

        System.out.println(answer);
    }

    public static int makeSubString(String[] s1, String[] s2, int startIndex) {
        String subString = "";
        int preIndex = 0;

        for (int i = startIndex; i < s1.length; i++) {
            String target = s1[i];
            int targetIndexOfs2 = findCharacterIndex(preIndex + 1, target, s2);
            if (targetIndexOfs2 != -1) {
                preIndex = targetIndexOfs2;
                subString += target;
            }
        }

        return subString.length();
    }

    public static int findCharacterIndex(int startIndex, String target, String[] s2) {
        for (int i = startIndex; i < s2.length; i++) {
            if (s2[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }
}
