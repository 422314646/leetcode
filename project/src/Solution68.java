/*
* 69. x 的平方根
* */
public class Solution68 {
    public int mySqrt(int x) {
        //二分查找方法
        int l = 0, r = x, ans = 0;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x){
                ans = mid;
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        Solution68 solution68 = new Solution68();
        System.out.println(solution68.mySqrt(5));
    }
}
