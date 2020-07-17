
/*
* 66.加一
* */
public class Solution66 {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--){
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0){
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        Solution66 solution66 = new Solution66();
        int[] digits = {9};
        int[] nums = solution66.plusOne(digits);
        for (int i :nums) {
            System.out.println(i);
        }
    }
}
