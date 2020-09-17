import java.util.ArrayList;
import java.util.List;

/*
*
* */
public class Solution119 {
    public List<Integer> getRow(int rowIndex) {
        return helper(rowIndex + 1).get(rowIndex);
    }

    private List<List<Integer>> helper(int num){
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.get(0).add(1);

        for (int i = 1; i < num; i++) {
            List<Integer> nums = new ArrayList<>();
            List<Integer> pre = res.get(i - 1);
            nums.add(1);
            for (int j = 1; j < i; j++) {
               nums.add(pre.get(j - 1) + pre.get(j));
            }
            nums.add(1);
            res.add(nums);
        }
        return res;
    }
    public static void main(String[] args) {
        Solution119 solution118 = new Solution119();
        System.out.println(solution118.getRow(3));
    }
}
