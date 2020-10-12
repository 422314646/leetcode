import java.util.Arrays;
import java.util.Collections;

/*
* 53. 最大子序和
* */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        //暴力破解
        int sum = 0 , maxSum = nums[0];
        for (int i = 0; i < nums.length; i++){
            sum = 0;
            for (int j = i; j < nums.length; j++){
                sum = nums[j] + sum;
                if (sum > maxSum){
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    //贪心算法
    public int maxSubArray1(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution53 solution53 = new Solution53();
        int[] nums = {-2};
        System.out.println(solution53.maxSubArray2(nums));
    }

    //动态规划
    private int maxSubArray2(int[] nums){
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i - 1] > 0) {
                nums[i] = nums[i] + nums[i - 1];
            }
        }
        Arrays.sort(nums);
        return nums[length - 1];
    }
}
