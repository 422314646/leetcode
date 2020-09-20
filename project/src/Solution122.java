/*
* 122. 买卖股票的最佳时机 II
* */
public class Solution122 {
    public int maxProfit(int[] prices) {
        int flag = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            flag = prices[i] - prices[i - 1];
            if (flag > 0){
                max = max + flag;
            }
        }
        return max;
    }
}
