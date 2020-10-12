import java.util.HashSet;
import java.util.Set;

/*
* 3. 无重复字符的最长子串
* */
public class Solution3 {
    public static void main(String[] args) {

    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> map = new HashSet<>();
        int ans = 0, left = -1;
        for (int i = 0; i < s.length(); i++) {
            if (i != 0){
                map.remove(s.charAt(i - 1));
            }
            while ((left + 1) < s.length() && !map.contains(s.charAt(left + 1))){
                map.add(s.charAt(left + 1));
                left++;
            }
            ans =  Math.max(ans, left - i + 1);
        }
        return ans;
    }
}
