import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
* 136. 只出现一次的数字
* */
public class Solution136 {
    public static int singleNumber(int[] nums) {
       int ans = 0;
        for (int num: nums) {
            ans ^= num;
        }
        return ans;
    }

    public static int singleNumber1(int[] nums){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i: nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int key: map.keySet()) {
            if (map.get(key) == 1){
                return key;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {2,2,1};
        System.out.println(singleNumber(nums));
    }
}
