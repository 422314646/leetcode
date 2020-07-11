
/*
* 59. 螺旋矩阵 II
* */
public class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int numElement = n * n, flag = 1;
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (numElement >= 1){
            for (int i = left; i <= right && numElement >= 1; i++){
                matrix[top][i] = flag;
                flag++;
                numElement--;
            }
            top++;
            for (int i = top; i <= bottom && numElement >= 1; i++){
                matrix[i][right] = flag;
                flag++;
                numElement--;
            }
            right--;
            for (int i = right; i >= left && numElement >= 1; i--){
                matrix[bottom][i] = flag;
                flag++;
                numElement--;
            }
            bottom--;
            for (int i = bottom; i >= top && numElement >= 1; i--){
                matrix[i][left] = flag;
                flag++;
                numElement--;
            }
            left++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        Solution59 solution59 = new Solution59();
        for (int[] row: solution59.generateMatrix(9)) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
