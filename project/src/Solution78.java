import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* 78. 子集
* */
public class Solution78 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        findSubset(0, stack, nums);
        return res;
    }

    public void findSubset(int begin, Stack<Integer> pre, int[] nums){
        res.add(new ArrayList<>(pre));
        for (int i = begin; i < nums.length; i++){
            pre.push(nums[i]);
            findSubset(i + 1, pre, nums);
            pre.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution78 solution78 = new Solution78();
        for (List<Integer> list: solution78.subsets(nums))
            System.out.println(list);
    }
}
