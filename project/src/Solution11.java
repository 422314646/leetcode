/*
* 11. 盛最多水的容器
* */
public class Solution11 {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int tmp = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(tmp, maxArea);
            if (height[left] >= height[right]){
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }
}
