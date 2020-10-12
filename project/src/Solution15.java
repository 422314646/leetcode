import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
* 15. 三数之和
* */
public class Solution15 {

    private static List<List<Integer>> res1 = new ArrayList<>();
    private static List<List<Integer>> res2 = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Stack<Integer> path = new Stack<>();
        threeSum1(nums, 0, path);
        for (int i = 0; i < res1.size(); i++) {
            if (res1.get(i).get(0) + res1.get(i).get(1) + res1.get(i).get(2) == 0){
                res2.add(res1.get(i));
            }
        }
        System.out.println(res2);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3){
            return res;
        }
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int left = i, mid = i + 1, right = n - 1;
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            while (mid < right && nums[left] <= 0) {
                if (nums[left] + nums[mid] + nums[right] == 0){
                    res.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                    while (mid < right && nums[mid] == nums[mid + 1]){
                        mid++;
                    }
                    while (mid < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    mid++;
                } else if (nums[left] + nums[mid] + nums[right] < 0) {
                    mid++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void threeSum1(int[] nums, int begin, Stack<Integer> path){
        if (path.size() == 3){
            res1.add(new ArrayList<>(path));
        }
        for (int i = begin; i < nums.length; i++) {
            path.push(nums[i]);
            threeSum1(nums, i + 1, path);
            path.pop();
        }
        //return res1;
    }
}
