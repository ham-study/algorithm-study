import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int subin = Integer.parseInt(input[0]);
        int sister = Integer.parseInt(input[1]);

        int sum = 0;
        int tempLocation = subin;
        while (subin <= sister) {
            if (subin == sister) {
                break;
            }

            if (tempLocation * 2 < sister) {
                tempLocation = tempLocation * 2;
            }
        }
    }
}
