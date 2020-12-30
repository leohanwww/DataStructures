package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    ArrayList<String> vertexList; //存储顶点的集合
    int[][] edges; //存储图对应的临接矩阵
    int numberOfEdges; //边的数量
    //记录某个顶点是否被访问过
    boolean[] isVisted;

    public static void main(String[] args) {
        //创建一个图
        int n = 5;
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String s : vertexValue) { //添加顶点
            graph.insertVertex(s);
        }
        //添加边
        graph.addEdges(0, 1, 1);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 2, 1);
        graph.addEdges(1, 3, 1);
        graph.addEdges(1, 4, 1);
        //显示矩阵
        graph.showGraph();
        //测试dfs深度遍历
//        System.out.println("深度遍历");
//        graph.dfs();
        //测试广度优先
        System.out.println("广度遍历");
        graph.bfs();
    }

    public Graph(int n) {
        //初始化矩阵vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numberOfEdges = 0;
        isVisted = new boolean[5];
    }

    //得到第一个邻接节点的下标

    /**
     * description
     *
     * @param index 根据index开始找它的第一个邻接的下标
     * @return int 存在就返回对应的下标,否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点获取下一个邻接节点

    /**
     * description
     *
     * @param v1 已经被访问过的
     * @param v2 找到的已经被访问过的i的邻接节点
     * @return int
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历
    public void dfs(boolean[] isVisted, int i) {
        //访问该节点
        System.out.printf(getValueByIndex(i) + "->");
        //将该节点设为已访问
        isVisted[i] = true;
        //找到i这个访问过的节点的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) { //有邻接节点
            if (!isVisted[w]) { //没被访问过
                dfs(isVisted, w);
            }
            //w已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs重载,遍历所有节点并进行dfs
    public void dfs() {
        //遍历所有节点,进行dfs[回溯]
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisted[i]) {
                dfs(isVisted, i);
            }
        }
    }

    //广度优先遍历(一个节点)
    public void bfs(boolean[] isVisited, int i) {
        int u; //队列头节点对应的下标
        int w; //邻接节点下标
        //队列,记录节点访问顺序
        LinkedList queue = new LinkedList();
        //访问节点,输出
        System.out.printf(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) { //只要队列非空
            //取出队列头节点
            u = (Integer) queue.removeFirst();
            //得到第一个邻接点
            w = getFirstNeighbor(u);
            while (w != -1) { //找到且没被访问
                if (!isVisited[w]) {
                    System.out.printf(getValueByIndex(w) + "->");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //w已经访问过,找u的下一个邻接点,体现出广度优先
                w = getNextNeighbor(u, w);
            }
        }

    }

    //遍历所有节点,进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisted[i]) {
                bfs(isVisted, i);
            }
        }
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * description
     *
     * @param v1     点的下标,边的开始点开始是第几个顶点
     * @param v2     点的下标,边的结束点是第几个顶点
     * @param weight 一般为1
     * @return void
     */
    public void addEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }

    //获得节点个数
    public int getNumberOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    //返回节点i对应的值0-"A" 1-"B" 2-"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
