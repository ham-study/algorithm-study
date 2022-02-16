package 카카오_3_징검다리건너기;

public class Solution {
    
    public static int solution(int[] stones, int k) {

        int left = 0;
        int right = findMax(stones);

        // 건널수 없는 사람수의 최소값을 찾는다
        while(left < right){
            int mid = (left + right) / 2;
            
            // 건널수 있다면 숫자 키움
            if(canPass(stones, k, mid)){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }

    public static int findMax(int[] arr){
        int max = 0;
        for(int num : arr){
            if(num > max){
                max = num;
            }
        }
        return max;
    }

    public static boolean canPass(int[] stones, int k, int mid){

        int count = 0;

        for(int stone : stones){
            stone -= mid;
            if(stone <= 0){
                count ++;
            } else{
                count = 0;
                continue;
            }
            if(count == k){
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        // 3
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
        
        System.out.println(solution(new int[]{1}, 2));

    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/64062
 * 성공여부 : 실패
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * ================================================================================
 * 
 * 
 */
