import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
* 54. 螺旋矩阵
* */
public class Solution54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0){
            return result;
        }
        int numElement = matrix.length * matrix[0].length;
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        while (numElement >= 1){
            for (int i = left; i <= right && numElement >= 1; i++){
                result.add(matrix[top][i]);
                numElement--;
            }
            top++;
            for (int i = top; i <= bottom && numElement >= 1; i++){
                result.add(matrix[i][right]);
                numElement--;
            }
            right--;
            for (int i = right; i >= left && numElement >= 1; i--){
                result.add(matrix[bottom][i]);
                numElement--;
            }
            bottom--;
            for (int i = bottom; i >= top && numElement >= 1; i--){
                result.add(matrix[i][left]);
                numElement--;
            }
            left++;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution54 solution54 = new Solution54();
        //int[][] num = {{1, 2, 3},{4, 5, 6},{7, 8, 9 }};
        int[][] num ={};
        System.out.println(solution54.spiralOrder(num));
    }
}
