import java.util.Arrays;

/*
*  75.颜色分类
* */
public class Solution75 {
    public static void sortColors(int[] nums) {
        int p0 = 0, p1 = nums.length - 1, curr = 0;
        int temp;
        while (curr <= p1){
            if (nums[curr] == 2){
                temp = nums[p1];
                nums[p1] = nums[curr];
                nums[curr] = temp;
                p1--;
            } else if (nums[curr] == 0){
                temp = nums[p0];
                nums[p0] = nums[curr];
                nums[curr] = temp;
                p0++;
                curr++;
            } else {
                curr++;
            }
        }
    }


    public static void bubbleSort(int[] nums){

        int temp;
        for (int i = 0; i < nums.length - 1; i++){
            boolean flag = false;
            for (int j = 0; j < nums.length - 1 - i; j++){
                if (nums[j] > nums[j + 1]){
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }

    public static void selectSort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++){
            int minIndex = i;
            int min = nums[i];
            for (int j = i + 1; j < nums.length; j++){
                if (min > nums[j]){
                    minIndex = j;
                    min = nums[j];
                }
            }
            if (minIndex != i){
                nums[minIndex] = nums[i];
                nums[i] = min;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,0,2,0};
        sortColors(nums);
        //bubbleSort(nums);
        //selectSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
