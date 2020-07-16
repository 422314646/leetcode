
/*
* 63. 不同路径 II
* */
public class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0){
            return 0;
        }
        int row = obstacleGrid.length, column = obstacleGrid[0].length;
        int[][] dp = new int[row][column];
        for (int i =0; i < row && obstacleGrid[i][0] == 0; i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < column && obstacleGrid[0][i] == 0; i++){
            dp[0][i] = 1;
        }

        for (int i = 1; i < row; i++){
            for (int j = 1; j < column; j++){
                if (obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][column - 1];
    }

    public int uniquePathsWithObstacles1(int[][] obstacleGrid){
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++){
            for (int j = 0; j < obstacleGrid[0].length; j++){
                if (i == 0 && obstacleGrid[i][j] != 1){
                    dp[i][j] = 1;
                }else if (j == 0 && obstacleGrid[i][j] != 1){
                    dp[i][j] = 1;
                }else if (obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }else if (i == 0 && j == 0){
                    dp[i][j] = 1;
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    public static void main(String[] args) {
        Solution63 solution63 = new Solution63();
        int[][] obstacleGrid  = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(solution63.uniquePathsWithObstacles1(obstacleGrid));
        System.out.println(solution63.uniquePathsWithObstacles(obstacleGrid));
    }
}
