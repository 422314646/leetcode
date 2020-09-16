package horse;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseChess {

    private static int X; //棋盘的列数
    private static int Y; //棋盘的行数
    //数组表示棋盘各个位置是否被访问过
    private static boolean visited[];
    //标记是否棋盘所有的位置都被访问过
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traverChess(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        for (int[] rows: chessboard){
            for(int step: rows){
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }
    //马踏棋盘的算法
    /*
    * chessboard 棋盘
    * row 马儿当前位置的行
    * column 马儿当前位置的列
    * step 马儿走到第几步
    * */
    public static void traverChess(int[][] chessboard, int row, int column, int step){
        //记录第几步走的哪一个位置
        chessboard[row][column] = step;
        //标记改访问的位置
        visited[row * X + column] = true;
        //获取下一步可走位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //导入贪心算法进行优化
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出第一个可以走的位置
            //判断该点是否已经访问
            if (!visited[p.y * X + p.x]){
                traverChess(chessboard, p.y, p.x, step + 1);
            }
        }
        if (step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] =false;
        } else {
            finished = true; //找到正确答案，为了跳出if (step < X * Y && !finished) 跳出其他循环
        }

    }

    //根据当前位置，计算马儿还能走哪些位置，并放入到一个集合中，最多8个位置
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //马儿是否能走5号位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //马儿是否能走6号位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //马儿是否能走7号位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //马儿是否能走0号位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //马儿是否能走1号位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        //马儿是否能走2号位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //马儿是否能走3号位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //马儿是否能走4号位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前位置的下一个位置的大小进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count = next(o1).size();
                int count1 = next(o2).size();
                if (count < count1){
                    return -1;
                } else if (count == count1){
                    return 0;
                } else {
                    return -1;
                }

            }
        });
    }
}
