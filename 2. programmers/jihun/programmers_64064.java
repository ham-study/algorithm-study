/******************************************************************************

 문제출처 : https://programmers.co.kr/learn/courses/30/lessons/64064
 풀이시간 : 30m
 시간복잡도 : O(2^n * n) ~ O(2^8 * 8) ~O(2048)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.IOException;
import java.util.*;

class Solution {
    private String[] user_ids;
    private int bannedSize;
    private int answer;
    private String[] banned_ids;
    private Set<Set<String>> answerSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        bannedSize = banned_id.length;
        user_ids = user_id;
        banned_ids = banned_id;

        doPermutation(new boolean[user_id.length], new LinkedHashSet<String>());

        return answer;
    }

    private void doPermutation(boolean[] selected, LinkedHashSet<String> result) {
        if (result.size() == bannedSize) {
            if (!isAnswer(result)) return;

            Set<String> tmpSet = getResultSet(result);
            if (answerSet.contains(tmpSet)) return;

            answerSet.add(tmpSet);
            answer++;

            return;
        }

        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) continue;

            selected[i] = true;
            result.add(user_ids[i]);
            doPermutation(selected, result);
            result.remove(user_ids[i]);
            selected[i] = false;
        }
    }

    private boolean isAnswer(LinkedHashSet<String> ids) {
        int i = 0;

        for (var targetId : ids) {
            String bannedIdStr = banned_ids[i];
            i++;

            if (bannedIdStr.length() != targetId.length()) return false;

            for (int j = 0; j < targetId.length(); j++) {
                char bannedCh = bannedIdStr.charAt(j);

                if (bannedCh == '*') continue;

                if (bannedCh != targetId.charAt(j)) return false;
            }
        }

        return true;
    }

    private Set<String> getResultSet(LinkedHashSet<String> result) {
        HashSet<String> tmp = new HashSet<>();
        for (var str : result) {
            tmp.add(str);
        }

        return tmp;
    }

    public static void main(String[] args) throws IOException {
        new Solution().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"});
    }
}
