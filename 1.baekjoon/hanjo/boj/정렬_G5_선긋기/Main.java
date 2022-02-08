package 정렬_G5_선긋기;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] coords = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            coords[i][0] = Integer.parseInt(st.nextToken());
            coords[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(coords));
    }


    public static int solution(int[][] coords){
        
        // for(int i=0; i<coords.length; i++){
        //     Arrays.sort(coords[i]);
        // }
        Arrays.sort(coords, (o1, o2) -> {
            int result = o1[0] - o2[0];
            if(result == 0){
                result = o1[1] - o2[1];
            }
            return result;
        });

        int count = 0;
        int[] cur = new int[]{coords[0][0], coords[0][1]};
        for(int[] coord : coords){

            // System.out.println(Arrays.toString(cur));
            // System.out.println(count);
            

            if(cur[1] >= coord[0]){
                if(cur[1] <= coord[1])
                    cur[1] = coord[1];
            }
            else{
                count += cur[1] - cur[0];
                cur[0] = coord[0];
                cur[1] = coord[1];
            }
        }

        // System.out.println(Arrays.toString(cur));
        // System.out.println(count);
        count += cur[1] - cur[0];

        return count;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2170
 * 성공여부 : 
 * 풀이시간 : 
 * 
 * 시간복잡도 : 
 * 메모리 : KB
 * 소요 시간 : ms
 * ================================================================================
 * 
 * 
 */ 