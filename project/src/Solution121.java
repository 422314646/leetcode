/*
* 121. 买卖股票的最佳时机
* */
public class Solution121 {
    public int maxProfit(int[] prices) {
        Integer max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if ((prices[j] - prices[i]) > max){
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;
    }

    public int maxProfit1(int[] prices){
        int minVal = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minVal){
                minVal = prices[i];
            } else if (prices[i] - minVal > max){
                max = prices[i] - minVal;
            }
        }
        return max;
    }
}
