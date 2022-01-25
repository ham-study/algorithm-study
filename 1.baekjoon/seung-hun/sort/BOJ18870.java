import java.io.*;
import java.util.*;

public class BOJ18870 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> sorted = removeDuplicatedAndSort(arr);
        
        StringBuilder sb = new StringBuilder();
        
        for (int num : arr) {
            sb.append(lowerBound(sorted, num)).append(" ");
        }
        
        System.out.println(sb);
    }
    
    private static int lowerBound(List<Integer> list, int target) {
        int left = -1, right = list.size();
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (list.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return right;
    }
    
    private static List<Integer> removeDuplicatedAndSort(int[] arr) {
        List<Integer> result = removeDuplicated(arr);
        Collections.sort(result);
        return result;
    }
    
    private static List<Integer> removeDuplicated(int[] arr) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> exist = new HashSet<>();
        
        for (int i=0;i<arr.length;i++) {
            if (exist.contains(arr[i])) continue;
            result.add(arr[i]);
            exist.add(arr[i]);
        }
        
        return result;
    }
}
/***
 * 정렬 후 lower bound
 * 소요시간: 20분
 * 시간복잡도: O(NlogN)
 * 공간복잡도: O(N)
 */
/*
import java.io.*;
import java.util.*;

public class MyClass {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        Set<Integer> set = new TreeSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            
            set.add(arr[i]);
        }
        
        Map<Integer, Integer> idxMap = new HashMap<>();
        
        int idx = 0;
        for (int num : set) {
            idxMap.put(num, idx++);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(idxMap.get(num)).append(" ");
        }
        
        System.out.println(sb);
    }
    
}
*/