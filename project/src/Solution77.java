import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
* 77.组合
* */
public class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0){
            return res;
        }
        Stack<Integer> pre = new Stack<>();
        findCombinations(n, k, 1, pre);
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();

    public void findCombinations(int n, int k, int begin, Stack<Integer> pre){
        if (pre.size() == k){
            res.add(new ArrayList<>(pre));
            return;
        }
        for (int i = begin; i <= n; i++){
            pre.push(i);
            findCombinations(n, k, i + 1, pre);
            pre.pop();
        }
    }

    public static void main(String[] args) {
        Solution77 solution77 = new Solution77();
        for (List<Integer> list: solution77.combine(4, 2)){
            System.out.println(list);
        }
    }
}
