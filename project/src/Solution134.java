/*
*134. 加油站
* */
public class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //贪心算法
        int residue = 0;
        int minOil = 0;

        for (int i = 0; i < gas.length; i++) {
            residue += gas[i] - cost[i];
            minOil = Math.min(residue, minOil);
        }
        if (residue < 0){
            return -1;
        }
        if (minOil >= 0){
            return 0;
        }
        //如果min<0，那么考虑更换起点，从右往左枚举起点，此时行驶方向还是从左往右，将min补充到大于0的点就是能够环绕一周的起始点，因为此时从新的起点出发，那么min>=0，这表示行驶过程中油箱始终不为空。
        for (int i = gas.length - 1; i >= 0; i--) {
            int oil = gas[i] - cost[i];
            minOil = minOil + oil;
            if (minOil >= 0){
                return i;
            }
        }
        return -1;
    }

}
