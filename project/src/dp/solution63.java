package dp;

public class solution63 {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //int row = obstacleGrid[0].length, column = obstacleGrid.length;
        int row = obstacleGrid.length, column = obstacleGrid[0].length;
        int[][] dp = new int[row][column];

        for (int i =0; i < row && obstacleGrid[i][0] == 0; i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < column && obstacleGrid[0][i] == 0; i++){
            dp[0][i] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][column - 1];
    }

    public static void main(String[] args) {
        int[][] op = {{1,0}};
        System.out.println(uniquePathsWithObstacles(op));
    }
}
