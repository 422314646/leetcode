
/*
*80. 删除排序数组中的重复项 II
* */
public class Solution80 {

    public static int removeDuplicates(int[] nums) {

        int count = 0;
        int length = nums.length;
        for (int i = 0; i < length - 1; i++){
            if (nums[i] == nums[i + 1]){
                count++;
                if (count >= 2){
                    length--;
                    remElement(nums, i);
                    i--;
                    count--;
                }
                continue;
            }else {
                count = 0;
            }
        }
        return length;
    }

    public static int[] remElement(int[] arr, int index){
        for (int i = index + 1; i < arr.length - 1; i++){
            arr[i] = arr[i + 1];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,2,2,3,3,3};
        System.out.println(removeDuplicates(nums));
    }
}
