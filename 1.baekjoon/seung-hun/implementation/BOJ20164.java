import java.io.*;
class BOJ20164 {
    static int min = Integer.MAX_VALUE;
    static int max = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int N = Integer.parseInt(br.readLine());
        
        dfs(N, countOdds(N));

        System.out.println(min +" " + max);
    }

    private static void dfs(int n, int count) {
        if (n < 10) {
            min = Math.min(count, min);
            max = Math.max(count, max);
            return;
        }

        if (n < 100) {
            int first = n % 10;
            int second = n / 10;
            
            int newNum = first + second;

            dfs(newNum, count + countOdds(newNum));
            return;
        }

        String num = String.valueOf(n);

        for (int i=1;i<num.length()-1;i++) {
            for (int j=i+1;j<num.length();j++) {
                int first = Integer.parseInt(num.substring(0, i));
                int second = Integer.parseInt(num.substring(i, j));
                int third = Integer.parseInt(num.substring(j));

                int newNum = first + second + third;

                dfs(newNum, count + countOdds(newNum));
            }
        }
    }

    private static int countOdds(int newNum) {
        int count = 0;
        while (newNum > 0) {
            int remain = newNum % 10;

            count += remain % 2;

            newNum /= 10;
        }

        return count;
    }
}