import java.util.Arrays;

/*
* 62. 不同路径
* */
public class Solution62 {
    //动态规划
    public int uniquePaths(int m, int n) {
        int[][] dp =new int[m][n];
        for (int i = 0; i < m; i++){
           dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                //System.out.println(dp[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths1(int m, int n){
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0],1);//把第一行全部填充为1
        for (int i = 1; i < m; i++){
            dp[i][0] = 1;
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution62 solution62 = new Solution62();
        int num = solution62.uniquePaths1(3,2);
        System.out.println(num);
    }
}
