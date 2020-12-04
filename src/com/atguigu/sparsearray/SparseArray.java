package com.atguigu.sparsearray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//稀疏数组--二维数组
public class SparseArray {
    public static void main(String[] args) {
        //原始二维数组
        //0表示无子 1表示黑子 2表示蓝子
        int[][] cheeseArr1 = new int[11][11];
        cheeseArr1[1][2] = 1;
        cheeseArr1[2][3] = 2;
        cheeseArr1[6][5] = 2;
        for (int[] row : cheeseArr1) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
        int sum = 0; //获取非0数据的个数
        for (int i = 0; i < 11; i++) { //行
            for (int j = 0; j < 11; j++) { //列
                if (cheeseArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //创建稀疏数组
        int[][] spraseArr = new int[sum + 1][3]; //有sum+1行,3列
        //给稀疏数组赋值
        //第一行是11 11 2
        spraseArr[0][0] = 11;
        spraseArr[0][1] = 11;
        spraseArr[0][2] = sum;
        //遍历二维数组,将非0的值放到稀疏数组中
        int row = 1;
        for (int i = 0; i < 11; i++) { //行
            for (int j = 0; j < 11; j++) { //列
                if (cheeseArr1[i][j] != 0) {
                    spraseArr[row][0] = i;
                    spraseArr[row][1] = j;
                    spraseArr[row][2] = cheeseArr1[i][j];
                    row++;

                }
            }
        }
        for (int[] ints : spraseArr) {
            for (int data : ints) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
        //稀疏数组恢复成原始数组
        int r = spraseArr[0][0];
        int l = spraseArr[0][1];
        //创建新的数组
        int[][] newCheeseArr = new int[r][l];
        //将稀疏数组的数据放到新的二维数组中
        //从稀疏数组第1行开始遍历
        for (int i = 1; i< spraseArr.length;i++){
            newCheeseArr[spraseArr[i][0]][spraseArr[i][1]] = spraseArr[i][2];
             //获取到二维数组的列    //获取到二维数组的行         //获取到二维数组的数值
        }
        System.out.println("新的二维数组:");
        for (int[] f : newCheeseArr) {
            for (int data : f) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
        //将稀疏数组保存到磁盘
        File file = new File("d:/develop/datastructures/map.data");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            for (int[] ints : spraseArr) {
                assert fileWriter != null;
                fileWriter.write(ints[0]);
                fileWriter.write(ints[1]);
                fileWriter.write(ints[2]);
                fileWriter.write("\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
