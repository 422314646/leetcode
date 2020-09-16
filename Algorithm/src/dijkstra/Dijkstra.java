package dijkstra;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] {N, 5, 7, N, N, N, 2};
        matrix[1] = new int[] {5, N, N, 9, N, N, 3};
        matrix[2] = new int[] {7, N, N, N, 8, N, N};
        matrix[3] = new int[] {N, 9, N, N, N, 4, N};
        matrix[4] = new int[] {N, N, 8, N, N, 5, 4};
        matrix[5] = new int[] {N, N, N, 4, 5, N, 6};
        matrix[6] = new int[] {2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        //测试
        graph.dsj(6);
        graph.show();
    }
}

class Graph{
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv; //已经访问的顶点集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph(){
        for (int[] link: matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //Dijkstra算法实现
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index);//跟新index顶点到周围顶点的距离和前驱顶点
        for (int i = 0; i < vertex.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    public void show(){
        vv.show();
    }

    //跟新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.in(i) && len < vv.getDis(i)){
                vv.updatePre(i, index);
                vv.updateDis(i, len);
            }
        }
    }
}

//已经访问节点
class VisitedVertex{
    //记录各个顶点是否访问过1表示访问过，0没有访问
    public int[] already_arr;
    //每一个下标的前一个顶点下标
    public int[] pre_visited;
    //记录出发点到其他顶点的距离
    public int[] dis;

    //length顶点个数， index出发顶点对应的下标
    public VisitedVertex(int length, int index){
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis
        Arrays.fill(dis,655335);
        this.already_arr[index] = 1;
        this.dis[index] = 0;// 设置出发顶点的距离为0
    }

    //判断index是否被访问过
    //访问过返回true，没有访问过返回false
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    //跟新出发点到index的距离
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    //更新顶点的前驱为index节点
    public void updatePre(int pre, int index){
        pre_visited[pre] = index;
    }

    //返回出发点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }

    public int updateArr(){
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后结果
    public void show(){
        System.out.println("=============================");
        for (int i: already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==========================");
        for (int i: pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==========================");
        for (int i: dis) {
            System.out.print(i + " ");
        }
    }
}