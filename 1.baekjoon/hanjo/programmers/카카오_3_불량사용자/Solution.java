package 카카오_3_불량사용자;

import java.util.*;

public class Solution {

    public static ArrayList<ArrayList<String>> candidates;
    public static  ArrayList<HashSet<String>> combinations;

    public static int solution(String[] user_id, String[] banned_id) {

        // 후보 찾기
        candidates = new ArrayList<>();
        for(int i=0; i<banned_id.length; i++){

            String bannedId = banned_id[i];
            candidates.add(new ArrayList<>());

            for(String userId : user_id){
                if(isMatch(userId, bannedId)){
                    candidates.get(i).add(userId);
                }
            }
        }

        // ??
        combinations = new ArrayList<>();
        reculsive(0, new HashSet<String>());

        //
        ArrayList<ArrayList<String>> combList = new ArrayList<>();
        for(var combination : combinations){
            // hashset -> arraylist
            combList.add(new ArrayList<String>(combination));
        }

        int answer = combinations.size();

        for(int i=0; i<combList.size(); i++){
            int count = 0;
            for(int j=i; j<combList.size(); j++){
                for(int k=0; k<candidates.size(); k++){
                    String str1 = combList.get(i).get(k);
                    String str2 = combList.get(j).get(k);
                    if(str1.equals(str2)){
                        count++;
                    }
                }
            }
            if(count == candidates.size()){
                answer--;
            }
        }



        // for(var c : combinations){
        //     System.out.println(c);
        // }
        return answer;
    }

    public static boolean isMatch(String userId, String bannedId){
        if(userId.length() != bannedId.length()){
            return false;
        }
        for(int j=0; j<userId.length(); j++){
            if(bannedId.charAt(j) != userId.charAt(j) && bannedId.charAt(j) != '*'){
                return false;
            }
        }
        return true;
    }

    public static void reculsive(int depth, HashSet<String> bucket){
        if(depth == candidates.size()){
            if(bucket.size() == candidates.size()){
                combinations.add(bucket);
            }
            return;
        }

        for(String candidate : candidates.get(depth)){
            HashSet<String> newBucket = new HashSet<>();
            newBucket.addAll(bucket);
            newBucket.add(candidate);
            reculsive(depth+1, newBucket);
        }
    }


    public static void main(String[] args){
        // 2
        System.out.println(solution(
            new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
            new String[]{"fr*d*", "abc1**"}
        ));
        //2
        System.out.println(solution(
            new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
            new String[]{"*rodo", "*rodo", "******"}
        ));
        // 3
        System.out.println(solution(
            new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
            new String[]{"fr*d*", "*rodo", "******", "******"}
        ));
    }
}
