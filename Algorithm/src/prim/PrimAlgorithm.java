package prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.showGraph(mGraph);
        minTree.prim(mGraph, 0);
    }
}

//创建最小生成树
class MinTree{
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight){
        for (int i = 0; i < verxs; i++) { //顶点
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph){
        for (int[] link: graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
    //编写prim算法，得到最少生成树
    public void prim(MGraph graph, int v){
        int visited[] = new int[graph.verxs];
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 0; k < graph.verxs; k++) {
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + ',' + graph.data[h2] + ">权值：" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}


class MGraph{
    int verxs; //表示图的节点个数
    char[] data;
    int[][] weight;

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
