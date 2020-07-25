/*
* 74. 搜索二维矩阵
* */
public class Solution74 {

    //遍历二维数组不高效
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean flag = false;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j ++){
                if (target == matrix[i][j]){
                    flag = true;
                    break;
                }else if (target < matrix[i][j]){
                    break;
                }
            }
            if (flag){
                break;
            }
        }
        return flag;
    }

    //二分查找办法
    public boolean searchMatrix1(int[][] matrix, int target){
        int m = matrix.length;
        if (m == 0){
            return false;
        }
        int n = matrix[0].length;
        int left = 0, right = m * n - 1, mid = 0;
        if (m == 0){
            return false;
        }
        while (left <= right){
            mid = (left + right) / 2;
            if (target == matrix[mid / n][mid % n]){
                return true;
            }else if (target < matrix[mid / n][mid % n]){
                right = mid - 1;
            }else if (target > matrix[mid / n][mid % n]){
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix ={{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}};
        Solution74 solution74 = new Solution74();
        System.out.println(solution74.searchMatrix(matrix,3));
        System.out.println(solution74.searchMatrix1(matrix,3));

    }

}
