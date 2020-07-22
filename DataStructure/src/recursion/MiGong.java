package recursion;

import java.util.Arrays;

public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组迷宫
        int[][] map = new int[8][7];

        //使用1代表墙 上下全为1
        Arrays.fill(map[0], 1);
        Arrays.fill(map[7], 1);
        //左右全为1
        for (int i = 0; i < map.length; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        setWay(map, 1, 1);
        System.out.println("迷宫路线");
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //使用递归回溯来给小球找路
    //1.map表示地图
    //2.i，j表示地图的那个位置开始出发（1.1）
    //3.如果小球能到map[6][5]位置，则说明通路找到
    //4.约定：当map[i][j]为0表示该点没有走过当为1表示墙；2表示通路可以走；3表示该点已经走过，但是走不通
    //5.再走迷宫时，需要确定一个策略（方法） 下->右->上->左，如果该店走不通，在回溯
    public static boolean setWay(int[][] map, int i, int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                map[i][j] = 2;
                if (setWay(map, i + 1, j)){//向下走
                    return true;
                }else if (setWay(map, i, j + 1)){//向右走
                    return true;
                }else if (setWay(map, i - 1, j)){//向上走
                    return true;
                }else if (setWay(map, i, j - 1)){//向左走
                    return true;
                }else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            }else {//map不为0，只能是1.2.3
                return false;
            }
        }
    }

}
