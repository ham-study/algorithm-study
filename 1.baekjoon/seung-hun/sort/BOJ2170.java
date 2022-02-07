import java.io.*;
import java.util.*;
public class BOJ2170 {
    static class Point {
        public final int start;
        public final int end;
        
        Point(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString() {
            return start + " " + end;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long result = 0;
        List<Point> points = new ArrayList<>();
        
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            points.add(new Point(start, end));
        }
        
        Collections.sort(points, (p1, p2) -> Integer.compare(p1.start, p2.start));
        
        int left = points.get(0).start, right = points.get(0).end;
        
        for (int i=1;i<N;i++) {
            Point point = points.get(i);
            
            if (point.start > right) {
                result += (right - left);
                left = point.start;
                right = point.end;
            } else {
                right = Math.max(right, point.end);
            }
        }
        
        result += (right - left);
        
        System.out.println(result);
    }
}

/**
 * 소요시간: 15분
 * 시간복잡도: O(NlogN)
 * 공간복잡도: O(N)
 */


/***
 * ---
 *  ----
 *   ---
 *       -
 * 
 */