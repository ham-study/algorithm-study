package programmers;

import java.util.ArrayList;
import java.util.List;

public class PMS_64064 {

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        ArrayList<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < banned_id.length; i++) {
            List<String> possibleNames = findPossibleName(banned_id[i], user_id);
            lists.add(possibleNames);
        }

        return answer;
    }

    public static List<String> findPossibleName(String targetId, String[] user_id) {
        ArrayList<String> possibleNames = new ArrayList<>();
        String[] targetWords = targetId.split("");

        for (String user : user_id) {
            if (targetId.length() != user.length()) {
                continue;
            }
            for (int i = 0; i < targetWords.length; i++) {
                if (isPossible(targetWords, user)) {
                    possibleNames.add(user);
                }
            }
        }

        return possibleNames;
    }

    public static boolean isPossible(String[] targetWords, String userName) {
        String[] userWords = userName.split("");

        for (int i = 0; i < targetWords.length; i++) {
            if (targetWords[i].equals("*")) {
                continue;
            }
            if (!userWords[i].equals(targetWords[i])) {
                return false;
            }
        }

        return true;
    }

}
