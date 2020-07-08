/*
* 50. Pow(x, n)
* */
public class Solution50 {

    public double myPow(double x, int n) {
        return n >= 0? quickMul(x, n) : quickMul(x, n);
    }

    public double quickMul(double x, int n){
        if (n == 0){
            return 1.0;
        }
        double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    public static void main(String[] args) {
        Solution50 solution50 = new Solution50();
        System.out.println(solution50.myPow(2,3));
    }
}
