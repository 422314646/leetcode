package kruskal;

import java.util.Arrays;
import java.util.zip.Inflater;

public class KruskaCase {
    private int edgeNum; //边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A','B','C','D','E','F','G'};
        int matrix[][] = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        KruskaCase kruskaCase = new KruskaCase(vertexs, matrix);
        kruskaCase.Kruskal();
        /*kruskaCase.print();

        System.out.println(Arrays.toString(kruskaCase.getEdges()));*/
    }

    //构造器
    public KruskaCase(char[] vertexs, int[][] matrix){
        int vlen = vertexs.length;

        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    public void Kruskal(){
        int index = 0; //表示最后结果的索引
        int[] ends = new int[edgeNum];//保存已经有的最小生成树中的终点
        //创建结果数组
        EData[] rets = new EData[edgeNum];
        //获取图中所有边的集合，一共12边
        EData[] edges = getEdges();
        System.out.println("图的边集合=" + Arrays.toString(edges) + "共" + edges.length); //12
        //按照边的权值大小进行排序
        sortEdges(edges);
        //遍历edges，将边添加到最小生成树中时，怎么样判断是否形成回路
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            //获取第i条边的第二个顶点
            int p2 = getPosition(edges[i].end);
            //获取p1，p2这个顶点在已有最小生成树的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n){
                ends[m] = n; //设置m在“已有最小生成树”中的终点
                rets[index++] = edges[i];
            }
        }

        //统计打印“最小生成树”
        System.out.println("=========================");
        for (int i = 0; i < index; i++) {
            System.out.print(rets[i] + " ");
        }
        //System.out.println(Arrays.toString(rets));
    }

    //打印
    public void print(){
        System.out.println("邻接矩阵为：\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序，冒泡
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 -i; j++) {
                if (edges[j].weight > edges[j + 1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //顶点的值，返回顶点的下标
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    //获取图中边，放到EData数组中
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //获取i的顶点的终点，用于判断两个顶点终点是否相同
    //返回就是i下标为i的这个顶点对应终点的下标
    private int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

class EData{
    char start; //边的一个点
    char end; //边的另一个点
    int weight; //边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                " , " + end +
                ">=" + weight +
                '}';
    }
}