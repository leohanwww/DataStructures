package com.atguigu.recursion;

public class MazeDemo {
    public static void main(String[] args) {
        //二维数组模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1; //第一行
            map[7][i] = 1; //第7行
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1; //第一列
            map[i][6] = 1;//第7列
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("原地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }
        //递归回溯给小球找路
        setWay2(map, 1, 1);
        //输出新的地图
        System.out.println("寻路后的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * description
     *
     * @param map 地图
     * @param i   开始的坐标
     * @param j
     * @return boolean 找到返回true
     * 当map[i][j]为1表示墙,为0表示没走过,2表示可以走,3表示该点已经走过,但是不通
     * 走的策略是 下-右-上-左
     * 目标地址是int[6][5]
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //已经走通
            return true;
        } else {
            if (map[i][j] == 0) {//当前点还没走过
                //按照策略走
                map[i][j] = 2; //假设该点可以走通,
                if (setWay(map, i + 1, j)) {//i+1表示向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {//说明该点map[i][j]走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {//map[i][j]!=0,可能是1,2,3
                return false;
            }
        }

    }

    //上,右,下,左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //已经走通
            return true;
        } else {
            if (map[i][j] == 0) {//当前点还没走过
                //按照策略走
                map[i][j] = 2; //假设该点可以走通,
                if (setWay2(map, i - 1, j)) {//上
                    return true;
                } else if (setWay2(map, i, j + 1)) {//右
                    return true;
                } else if (setWay2(map, i + 1, j)) {//下
                    return true;
                } else if (setWay2(map, i, j - 1)) {//左
                    return true;
                } else {//说明该点map[i][j]走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {//map[i][j]!=0,可能是1,2,3
                return false;
            }
        }

    }
}
