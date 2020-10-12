import com.sun.javafx.collections.MappingChange;
import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.Stack;

/*
* 132. 分割回文串 II
* */
public class Solution132 {
    public int minCut(String s) {
       int len = s.length();
       if (len < 2){
           return 0;
       }
       int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }
        for (int i = 1; i < len; i++) {
            if (check(s, 0, i)){
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (check(s, j + 1, i)){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
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

    public int minCut1(String s){
        int len = s.length();
        if (len < 2){
            return 0;
        }

        boolean[][] dp = new boolean[len][len];
        int[] num = new int[len];
        for (int i = 0; i < len; i++) {
            num[i] = i;
        }
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && ((right - left) <= 2) || dp[left + 1][right - 1] == true){
                    dp[left][right] = true;
                }
            }
        }

        for (int i = 1; i < len; i++) {
            if (dp[0][i]){
                num[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (dp[j + 1][i]){
                    num[i] = Math.min(num[i], num[j] + 1);
                }
            }
        }
        return num[len - 1];
    }

    public static void main(String[] args) {
        String s = "aaba";
        Solution132 solution132 = new Solution132();

        System.out.println(solution132.minCut(s));
    }
}
