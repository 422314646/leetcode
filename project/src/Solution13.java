/*
* 13. 罗马数字转整数
* */
public class Solution13 {
    public static void main(String[] args) {
        String string = "MCMXCIV";
        System.out.println(romanToInt(string));
    }

    public static int romanToInt(String s) {
        int sum = 0;
        int preNum = getValues(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValues(s.charAt(i));
            if (preNum < num) {
                sum = sum - preNum;
            } else {
                sum = sum + preNum;
            }
            preNum = num;
        }
        sum = sum + preNum;
        return sum;
    }

    private static int getValues(char c){
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
