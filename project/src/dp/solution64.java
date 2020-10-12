package dp;

public class solution64 {
    public int minPathSum(int[][] grid) {
        int row = grid.length, column = grid[0].length;
        for (int i = 1; i < row; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < column; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                grid[i][j] = Math.min(grid[i-1][j] + grid[i][j], grid[i][j-1]+ grid[i][j]);
            }
        }
        return grid[row-1][column-1];
    }
}
