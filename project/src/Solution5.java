/*
* 5. 最长回文子串
* */
public class Solution5 {
    public static void main(String[] args) {

    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2){
            return s;
        }
        int maxLen = 1;
        boolean[][] dp = new boolean[len][len];
        int begin = 0;

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) != s.charAt(j)){
                    dp[j][i] = false;
                } else {
                    if (i - j < 3){
                        dp[j][i] = true;
                    } else {
                        dp[j][i] = dp[j + 1][i - 1];
                    }
                }
                if (dp[j][i] && i - j + 1 > maxLen){
                    maxLen = i - j + 1;
                    begin = j;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
