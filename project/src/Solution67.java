
/*
* 67.二进制求和
* */
public class Solution67 {
    public String addBinary(String a, String b) {
        int num1 = Integer.parseInt(a, 2);
        int num2 = Integer.parseInt(b, 2);
        return Integer.toBinaryString(num1 + num2);
    }

    public String addBinary1(String a, String b){
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }


    public static void main(String[] args) {
        String a = "1010";
        String b = "11";
        Solution67 solution67 = new Solution67();
        System.out.println(solution67.addBinary1(a, b));
    }
}
