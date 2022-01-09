import java.io.*;
class BOJ11726 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if (N <= 3) {
		    System.out.println(N);
		    return;
		}
		
		int current = 1;
		int next = 2;
		
		for (int i=2;i<N;i++) {
		    int temp = next;
		    next = (next + current) % 10007;
		    current = temp % 10007;
		}
		
		System.out.println(next);
    }
}