import java.util.ArrayList;
import java.util.List;

/*
* 118. 杨辉三角
* */
public class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0){
            return res;
        }
        res.add(new ArrayList<>());
        res.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> num = new ArrayList<>();
            List<Integer> pre = res.get(i - 1);
            num.add(1);
            for (int j = 1; j < i; j++) {
                num.add(pre.get(j - 1) + pre.get(j));
            }
            num.add(1);
            res.add(num);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution118 solution118 = new Solution118();
        System.out.println(solution118.generate(2));
    }
}
