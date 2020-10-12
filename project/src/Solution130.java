import java.util.ArrayDeque;
import java.util.Queue;

public class Solution130 {
    public static void main(String[] args) {

    }

    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};

    public void bfsSolve(char[][] board) {
        //bfs广度优先遍历
        int row = board.length;
        if (row == 0){
            return;
        }
        int column = board[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        //第一列
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O'){
                queue.add(new int[]{i, 0});
            }
        }
        //最后一列
        for (int i = 0; i < row; i++) {
            if (board[i][column - 1] == 'O'){
                queue.add(new int[]{i, column - 1});
            }
        }
        //第一行
        for (int i = 0; i < column; i++) {
            if (board[0][i] == 'O'){
                queue.add(new int[]{0, i});
            }
        }
        //最后一行
        for (int i = 0; i < column; i++) {
            if (board[row - 1][i] == 'O'){
                queue.add(new int[]{row - 1, i});
            }
        }

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0], y = temp[1];
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++) {
                int x1 = x + dx[i], y1 = y + dy[i];
                if (x1< 0 || y1 < 0 || x1 >= row || y1 >= column || board[x1][y1] == 'X'){
                    continue;
                } else if (board[x1][y1] == 'O'){
                    queue.add(new int[]{x1, y1});
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y){
        //深度优先遍历
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != 'O'){
            return;
        } else if (board[y][x] == 'O'){
            board[y][x] = 'A';
        }
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0){
            return;
        }
        int column = board[0].length;

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O'){
                dfs(board, i, 0);
            }
        }
        for (int i = 0; i < row; i++) {
            if (board[i][column - 1] == 'O'){
                dfs(board, i, column - 1);
            }
        }
        for (int i = 0; i < column; i++) {
            if (board[0][i] == 'O'){
                dfs(board, 0, i);
            }
        }
        for (int i = 0; i < column; i++) {
            if (board[row - 1][i] == 'O'){
                dfs(board, row - 1, i);
            }
        }

    }
}
