import java.util.*;

public class Test5 {
    public static void main(String[] args) {

        int[] a = {1, 2, 3};
        int[] b = a;
        int[] c = {1, 2, 3};
        int[] d = {1, 2};

        System.out.println(a.hashCode());
        System.out.println(c.hashCode());
        
        // 배열의 equals는 a == b 랑 똑같다
        // 즉 주소값을 비교
        if(a == b){
            System.out.println("a == b");
        }
        if(a.equals(b)){
            System.out.println("a.equals(b)");
        }
        if(a.equals(c)){
            System.out.println("a.equals(c)");
        }

        if(Arrays.equals(a, c)){
            System.out.println("Arrays.equals(a, c)");
        }
        if(Arrays.equals(a, d)){
            System.out.println("Arrays.equals(a, d)");
        }

        System.out.println();
        

        Queue<Integer> qa = new LinkedList<>();
        qa.add(1);
        Queue<Integer> qb = new LinkedList<>();
        qb.add(1);

        System.out.println(qa.hashCode());
        System.out.println(qb.hashCode());

        if(qa == qb){
            System.out.println("qa == qb");
        }
        if(qa.equals(qb)){
            System.out.println("qa.equals(qb)");
        }


        ArrayList<Integer> aa = new ArrayList<>();
        aa.add(1);
        aa.add(2);

        ArrayList<Integer> ab = new ArrayList<>();
        ab.add(2);
        ab.add(1);

        System.out.println(aa.hashCode());
        System.out.println(ab.hashCode());


        // List<Queue<Integer>> temp = new ArrayList<>();
        // temp.add(qa);
        // temp.add(qb);


        // if(qa.equals(qb)){
        //     System.out.println(qa.hashCode());
        //     System.out.println(qb.hashCode());

        //     // System.out.println("qa.equals(qb))");
        // }
        
        // 배열은 equlas 사용시 주소값만 비교하므로 Arrays.equals()를 사용해야 내부 요소까지 비교함
        // but 컬렉션의 순서와 내용이 같다면 객체가 여러개여도 하나의 주소값을 공유한다
        // 근데 Queue에 1넣은거랑 ArrayList에 1넣은거랑 주소값이 같음. 이거 뭐지?
    
    }
}
