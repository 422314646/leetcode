import java.util.HashMap;

/*
* 137. 只出现一次的数字 II
* */
public class Solution137 {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int k:map.keySet()) {
            if (map.get(k) == 1){
                return k;
            }
        }
        return -1;
    }
}
