
/*
* 跳楼梯
* 动态规划 f(n) = f(n-1) + f(n-2)
* */
public class Solution70 {
    public static int climbStairs(int n) {
        int q = 0, p = 0, r = 1;
        for (int i = 1; i <= n; i++){
            q = p;
            p = r;
            r = q + p;
        }
        return r;
    }

    public static int climbStairs1(int n){
        int[] sum = new int[n + 1];
        if (n >= 2){
            sum[0] = 1;
            sum[1] = 1;
            for (int i = 2; i<= n; i++){
                sum[i] = sum[i - 1] + sum[i - 2];
            }
        }else if (n == 1){
            sum[1] = 1;
        }
        return sum[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs1(1));
        System.out.println(climbStairs(1));
    }
}
