package dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};  //物品重量
        int[] val = {1500, 3000, 2000}; //物品的价值 这里val[i]
        int m = 4;  //背包容量
        int n = val.length; //物品个数
        int[][] path = new int[n + 1][m + 1];

        //v[i][j] 表示前i个物品中能装入容量j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //初始化第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        //初始化第一行
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //动态处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0){
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
