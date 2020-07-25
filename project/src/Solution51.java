import java.util.ArrayList;
import java.util.List;

/*
* 52. N皇后 II
* */
public class Solution51 {

    public static void main(String[] args) {
        Solution51 solution51 = new Solution51();
        solution51.totalNQueens(8);
    }
    int max;
    int count;
    int[] queue;

    public int totalNQueens(int n) {
        if (n == 0){
            return 0;
        }
         max = n;
         queue = new int[max];
         check(0);
        System.out.println(count);
        return count;
    }

    //放第几个皇后
    public void check(int n){
        if (n == max){
            count++;
            return;
        }
        for (int i = 0; i < max; i++){
            queue[n] = i;
            if (jude(n)){
                check(n + 1);
            }
        }
    }

    //判断是否冲突
    public Boolean jude(int n){
        for (int i = 0; i < n; i++){
            if (queue[i] == queue[n] || Math.abs(n - i) == Math.abs(queue[n] - queue[i])){
                return false;
            }
        }
        return true;
    }

}
