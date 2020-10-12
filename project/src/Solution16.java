import java.util.Arrays;

/*
*16. 最接近的三数之和
* */
public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - ans)){
                    ans = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target){
                    right--;
                } else {
                    return ans;
                }
            }
        }
        return ans;
    }


}
