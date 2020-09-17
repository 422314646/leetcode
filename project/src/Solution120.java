import java.util.List;

/*
* 120. 三角形最小路径和
* */
public class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[][] matrix = new int[length][length];

        matrix[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < length; i++) {
            matrix[i][0] = matrix[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                matrix[i][j] = Math.min(matrix[i - 1][j - 1], matrix[i - 1][j]) + triangle.get(i).get(j);
            }
            matrix[i][i] = matrix[i - 1][i - 1] + triangle.get(i).get(i);
        }

        Integer minVal = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            if (matrix[length - 1][i] < minVal){
                minVal = matrix[length - 1][i];
            }
        }
        return minVal;
    }
}
