package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    private boolean[] isVisited; //表示是否被访问


    public static void main(String[] args) {

        String[] value = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(5);
        for (String v1: value) {
            graph.insertVertex(v1);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();
        System.out.println("深度优先遍历");
        //graph.dfs();
        System.out.println();
        System.out.println("=================");
        System.out.println("广度优先遍历");
        graph.bfs();
    }

    public Graph(int n){
        //初始化矩阵
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个零阶矩阵节点的下标
    private int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }
    //根据前一个零阶矩阵节点的下标获取下一个邻接矩阵节点
    public int getNextNeighbor(int v1, int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //深度优先算法
    private void dfs(boolean[] isVisited, int i ){
        System.out.print(getValueByIndex(i) + "->");
        //将节点设置为反问过
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]){
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }
    //对dfs 进行一个重载，遍历我们所有的节点，并进行dfs
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    //对一个节点进行广度优先算法
    private void bfs(boolean[] isVisited, int i){
        int u; // 表示队列的头节点对应的下标
        int w; // 领结节点w
        //队列，记录节点访问顺序
        LinkedList queue = new LinkedList();
        //访问节点输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列头部
            u = (int) queue.removeFirst();
            //得到邻接点的下标W
            w = getFirstNeighbor(u);
            while (w != -1){
                //是否访问过
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找到w后面的下一个邻接点
                w = getNextNeighbor(u , w);
            }
        }
    }

    //遍历所有节点都进行广度优先搜索
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }

    //返回节点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的个数
    public int getOfEdges(){
        return numOfEdges;
    }
    //返回节点对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1 v2的权值
    public int insertVertex(int v1, int v2){
        return edges[v1][v2];
    }
    //显示对应矩阵
    public void showGraph(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
