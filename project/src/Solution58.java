
/*
* 58. 最后一个单词的长度
* */
public class Solution58 {
    public static int lengthOfLastWord(String s) {
        int end = s.length() - 1, start = 0;
        while (end > 0 && s.charAt(end) == ' '){
            end--;
        }
        start = end;
        if (end < 0){
            return 0;
        }
        while (end > 0 && s.charAt(start) != ' '){
            start--;
        }
        return end - start;
    }

    public static int lengthOfLastWord1(String s){

        if (s == null || s.length() == 0){
            return 0;
        }
        int flag = 0;
        for (int i = s.length() - 1; i >= 0; i--){
            if (s.charAt(i) == ' '){
                if (flag == 0){
                 continue;
                }else {
                    break;
                }
            }
            flag++;
        }
        return flag;
    }

    public static void main(String[] args) {
        String s = "Hello World";
        System.out.println(lengthOfLastWord(s));
        System.out.println(lengthOfLastWord1(s));
    }
}
