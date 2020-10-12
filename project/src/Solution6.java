import java.util.ArrayList;
import java.util.List;

/*
* 6. Z 字形变换
* */
public class Solution6 {
    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        System.out.println(convert(s,3));

    }
    public static String convert(String s, int numRows) {
        if (numRows < 2){
            return s;
        }
        List<StringBuilder> row = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            row.add(new StringBuilder());
        }

        int i = 0, flag = -1;
        for (char c: s.toCharArray()) {
            row.get(i).append(c);
            if (i == 0 || i == numRows - 1){
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder: row) {
            res.append(stringBuilder);
        }
        return res.toString();
    }
}
