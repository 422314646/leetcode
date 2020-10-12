/*
* 回文数
* */
public class Solution9 {
    public static void main(String[] args) {
        int res = 121;
        isPalindrome(354);
    }

    public static boolean isPalindrome(int x) {
        int target = x;
        if (x < 0){
            return false;
        }
        if (x == 0){
            return true;
        }
        int ans = 0;
        while (x > 0){
            int pop = x % 10;
            ans = pop + ans * 10;
            x = x / 10;
        }
        System.out.println(ans);
        return ans == target;
    }
}
