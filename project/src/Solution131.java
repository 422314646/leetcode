import java.util.*;

public class Solution131 {
    public List<List<String>> partition(String s) {
        int length = s.length();
        List<List<String>> res = new ArrayList<>();
        if (length == 0){
            return res;
        }
        Stack<String> path = new Stack<>();
        helper(s, 0, length, path, res);
        return res;
    }

    private void helper(String s, int begin, int len, Stack<String> path, List<List<String>> res){
        if (begin == len) {
            System.out.println(path.size());
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            if (!check(s, begin, i)){
                continue;
            }
            path.push(s.substring(begin, i + 1));
            helper(s, i + 1, len, path, res);
            path.pop();
        }
    }

    private boolean check(String s, int left, int right){

        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //用动态规划预处理回文字符串
    public List<List<String>> partition1(String s){
        List<List<String>> res = new ArrayList<>();
        int length = s.length();
        if (length == 0) {
            return res;
        }
        boolean[][] dp = new boolean[length][length];
        //状态转移方程
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right <= length; right++) {
            for (int  left = 0; left <= right ; left++) {
                if (s.charAt(left) == s.charAt(right) && ((right - left) <= 2 || dp[left + 1][right - 1] == true)){
                    dp[left][right] = true;
                }
            }
        }
        Stack<String> stack = new Stack<>();
        helper1(s, 0, length, stack, res, dp);
        return res;

    }

    private void helper1(String s, int begin, int len, Stack<String> path, List<List<String>> res, boolean[][] dp){
        if (begin == len){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            if (!dp[begin][i]){
                continue;
            }
            path.push(s.substring(begin, i + 1));
            helper1(s, i + 1, len, path, res, dp);
            path.pop();
        }
    }

    public static void main(String[] args) {
        String s = "aaba";
        Solution131 solution131 = new Solution131();
        System.out.println(solution131.partition(s));
    }
}
