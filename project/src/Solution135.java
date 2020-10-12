import java.util.Arrays;

/*
*135. 分发糖果
* */
public class Solution135 {
    public int candy(int[] ratings) {
        int length = ratings.length;
        int[] res = new int[length];
        Arrays.fill(res, 1);

        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]){
                res[i] = res[i - 1] + 1;
            }
        }

        for (int i = length - 2; i >= 0 ; i--) {
            if (ratings[i] > ratings[i + 1]){
                res[i] = Math.max(res[i], res[i + 1] + 1);
            }
        }
        int sum = 0;
        for (Integer i: res) {
            sum = sum + i;
        }
        return sum;
    }
}
