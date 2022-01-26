import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());


    }

    public static boolean canAttack(int target1I, int target1J, int target2I, int target2J) {
        if (target1I == target2I || target1J == target2J) {
            return true;
        }
        if (Math.abs(target1I - target2I) == Math.abs(target1J - target2J)) {
            return true;
        }

        return false;
    }
}
