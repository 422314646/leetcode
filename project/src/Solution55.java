
/*
跳跃游戏
*/
public class Solution55 {
    //贪心算法
    public static boolean canJump(int[] nums) {
        int maxSize = 0;
        for (int i = 0; i < nums.length; i++){
            if (i <= maxSize){
                int flag = i + nums[i];
                if (flag > maxSize){
                    maxSize = flag;
                }
            }
        }
        if (maxSize >= nums.length - 1){
            return true;
        }
        return false;
    }

    //挨着跳
    public static boolean canJump1(int[] nums){
        int maxSize = 0;
        for (int i = 0; i < nums.length; i++){
            if (i > maxSize){
                return false;
            }
            maxSize = Math.max(maxSize, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump(nums));
        System.out.println(canJump1(nums));
    }
}
