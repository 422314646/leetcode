/*
* 81. 搜索旋转排序数组 II
* */
public class Solution81 {

    public static boolean search(int[] nums, int target) {
        if (nums.length == 0 || nums == null){
            return false;
        }
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right){
            mid = left + (right - left) / 2;
            if (target == nums[mid]){
                return true;
            }
            if (nums[mid] == nums[left]){
                left++;
                continue;
            }
            if (nums[mid] > nums[left]){
                if (nums[mid] > target && target >= nums[left]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else
                {
                if (nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums,0 ));
    }
}
