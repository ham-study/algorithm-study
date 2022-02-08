package 완전탐색_2_소수찾기;

import java.util.*;

public class Solution {

    public static int solution(String numbers) {

        String[] arr = numbers.split("");

        HashSet<Integer> set = new HashSet<>();

        return 0;
    }

    // 재귀로 순열 만들기
    public static void permutation(){
        
    }

    public static boolean isPrime(int number){
        if(number <= 1){
            return false;
        }
        for(int i=2; i<=number; i++){
            if(number%i == 0){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(solution("17"));
    }
}
