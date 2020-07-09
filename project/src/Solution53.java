
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
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution53.maxSubArray1(nums));
    }
}
