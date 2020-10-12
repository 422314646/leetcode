/*
* 14. 最长公共前缀
* */
public class Solution14 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        String prefixString=strs[0];
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i].indexOf(prefixString));
            while (strs[i].indexOf(prefixString) != 0){
                prefixString = prefixString.substring(0, prefixString.length() - 1);
                if (prefixString.isEmpty()){
                    return "";
                }
            }
        }
        return prefixString;
    }

    public static void main(String[] args) {
        String[] strings = {"flower","flow","flight"};

        System.out.println(longestCommonPrefix(strings));
    }

}
