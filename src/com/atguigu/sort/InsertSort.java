package com.atguigu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
//        int[] array = {101, 34, 119, 1};
//        insertSort(array);
        int arr2[] = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成[0,8000000)的随机数
        }
        long t1 = System.currentTimeMillis();
        insertSort(arr2);
        long t2 = System.currentTimeMillis();
        System.out.println("排序使用的时间是:" + (t2 - t1) + "毫秒");
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int insertVal = 0; //arr[i]; //待插入的数34
        int insertIndex = 0; // i - 1; //arr[1]前面的一个的下标
        for (int i = 1; i < arr.length; i++) { //从arr[1]开始遍历,因为认为前1个为有序
            //第一轮,{101,34,119,1}
            insertVal = arr[i];  //待插入的数暂定为数组排除开头的一个的第1个值,为34
            insertIndex = i - 1; //待插入的位置暂定为i的前一个位置
            //给insertVal找到插入位置,insertIndex>=0为了数组不越界
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {  //insertVal:34 arr[insertIndex]:101
                //加入待插入的数值小于待插入的前一个位置
                //要将arr[insertIndex]后移{101,34,119,1}=>{101,101,119,1}
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--; //-1达到退出循环的条件
            }
            //当退出while循环,说明插入位置找到insertIndex+1=0
            if (insertIndex + 1 != i) { //判断是否需要赋值
                arr[insertIndex + 1] = insertVal; //{34,101,119,1}
            }
            //System.out.println("第"+i+"轮插入后:"+ Arrays.toString(arr));
        }

    }

    public static void insertSort2(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertVal = arr[i];
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }


    }

}
