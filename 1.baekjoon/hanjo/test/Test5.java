import java.util.*;

public class Test5 {
    public static void main(String[] args) {

        int[] a = {1, 2, 3};
        int[] b = a;
        int[] c = {1, 2, 3};
        int[] d = {1, 2};
        
        // 배열의 equals는 a == b 랑 똑같다
        // 즉 주소값을 비교
        if(a == b){
            System.out.println("a == b");
        }
        if(a.equals(b)){
            System.out.println("a.equals(b)");
        }

        if(Arrays.equals(a, c)){
            System.out.println("Arrays.equals(a, c)");
        }
        if(Arrays.equals(a, d)){
            System.out.println("Arrays.equals(a, d)");
        }
    }
}
