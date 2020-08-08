import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution90 {

    List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        Solution90 solution90 = new Solution90();
        int[] nums = {1,2,2};
        System.out.println(solution90.subsetsWithDup(nums));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Stack<Integer> stack = new Stack<>();
        findList(nums, 0, stack);
        return res;
    }

    public void findList(int[] nums, int begin, Stack<Integer> stack){
        res.add(new ArrayList<>(stack));
        //System.out.println(stack);
        for (int i = begin; i < nums.length; i++){
            if (i > begin && nums[i] == nums[i - 1]){
                continue;
            }
            stack.push(nums[i]);
            findList(nums, i + 1, stack);
            stack.pop();
        }
    }
}
