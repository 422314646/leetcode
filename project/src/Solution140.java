import java.util.*;

/*
* 140. 单词拆分 II
* */
public class Solution140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        Set<String> set = new HashSet(wordDict);

        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        List<String> res = new ArrayList<>();
        if (dp[len]) {
            LinkedList<String> queue = new LinkedList<>();
            Helper(s, len, set, res, queue, dp);
        }
        return res;

    }

    private void Helper(String s, int end, Set<String> wordSet, List<String> res, LinkedList<String> queue, boolean[] dp){
        if (wordSet.contains(s.substring(0, end + 1))){
            queue.addFirst(s.substring(0, end));

            StringBuilder stringBuilder = new StringBuilder();
            for (String word: queue) {
                stringBuilder.append(word);
                stringBuilder.append(" ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            res.add(stringBuilder.toString());
            queue.removeFirst();
        }

        for (int i = 0; i < end - 1; i++) {
            if (dp[i]) {
                String suffix = s.substring(i + 1, end + 1);

                if (wordSet.contains(suffix)) {
                    queue.addFirst(suffix);
                    Helper(s, i, wordSet, res, queue, dp);
                    queue.removeFirst();
                }
            }
        }
    }
}
