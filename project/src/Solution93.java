import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
* 93. 复原IP地址
* */
public class Solution93 {

    public static void main(String[] args) {
        Solution93 solution93 = new Solution93();
        System.out.println(solution93.restoreIpAddresses("25525511135"));
    }

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len < 4 || len > 12){
            return res;
        }
        Deque<String> path = new ArrayDeque<>(4);
        int splitTime = 0;
        dfs(s, len, splitTime, 0, path, res);
        return res;
    }

    private int judeNum(String s, int left, int right){
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0'){
            return -1;
        }
        //转成int
        int res = 0;
        for (int i = left; i <= right; i++){
            res = res * 10 + s.charAt(i) - '0';
        }
        if (res > 255){
            return -1;
        }
        return res;
    }

    private void dfs(String s, int len, int split, int begin, Deque<String> path, List<String> res){
        if (begin == len){
            if (split == 4){
                res.add(String.join(".", path));
            }
            return;
        }

        if (len - begin < (4 - split) || len - begin > 3 * (4 - split)){
            return;
        }

        for (int i = 0; i < 3; i++){
            if (begin + i >= len){
                break;
            }

            int ip = judeNum(s, begin, begin + i);
            if (ip != -1){
                path.addLast(ip + "");
                dfs(s, len, split + 1 , begin + 1 + i, path, res);
                path.removeLast();
            }
        }
    }
}
