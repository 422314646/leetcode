
/*
* 64. 最小路径和
* */
public class Solution64 {

    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid[0].length; i++){
            if (i == 0){
                continue;
            }
            grid[0][i] = grid[0][i] + grid[0][i - 1];
        }
        for (int i = 0; i < grid.length; i++){
            if ( i == 0){
                continue;
            }
            grid[i][0] = grid[i][0] + grid[i - 1][0];
        }
        for (int i = 1; i < grid.length; i++){
            for (int j = 1; j < grid[0].length; j++){
                grid[i][j] = Math.min(grid[i - 1][j],grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public int minPathSum1(int[][] grid) {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (i == 0 && j == 0){
                    continue;
                }else if (i == 0){
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                }else if (j == 0){
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                }else {
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        Solution64 solution64 = new Solution64();
        System.out.println(solution64.minPathSum1(grid));
    }
}
