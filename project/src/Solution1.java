import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
1. 两数之和
*/
public class Solution1 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    private static int[] twoSum(int[] nums, int target){
        //int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i , j};
                }
            }
        }
        throw new RuntimeException("没得");
    }

    private static int[] twoSum1(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            if (map.containsKey(tmp) && map.get(tmp) != i){
                return new int[]{i, map.get(tmp)};
            }
        }
        throw new RuntimeException("没得");
    }
}
