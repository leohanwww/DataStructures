package com.atguigu.sort;



import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, -2};
//        System.out.println("排序前:");
//        System.out.println(Arrays.toString(arr));

        //测试冒泡排序的速度O(n^2),给80000个数据
        //创建80000个数据的数组
        int arr2[] = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] =(int) (Math.random()*8000000) ; //生成[0,8000000)的随机数
        }
        long t1 = System.currentTimeMillis();
        bubbleSort(arr2);
        long t2 = System.currentTimeMillis();
        System.out.println("排序使用的时间是:"+(t2-t1)+"毫秒");
//        System.out.println("排序后:");
//        System.out.println(Arrays.toString(arr));
    }

    //冒泡排序
    public static void bubbleSort(int [] array) {
        //第一趟排序将最大的排在最后
        int temp = 0;
        boolean flag = false;
        //冒泡排序时间复杂度O(n^2)
        for (int i = 0; i < array.length - 1; i++) { //有n元素,就要进行n-1趟的排序
            for (int j = 0; j < array.length - 1 - i; j++) { //每次只对前n-i个元素进行比较和移动,因为最后i个已经固定是最大的
                //如果前面的数比后面的数大,就交换
                if (array[j] > array[j + 1]) {
                    flag = true; //用来判断是否进行了交换
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
           // System.out.println("第" + (i + 1) + "趟排序后的数组:");
            //System.out.println(Arrays.toString(array));
            if (!flag) {
                //一次交换都没有发生,直接退出本次循环
                break;
            } else {
                flag = false;//重置flag,进行下次判断
            }
        }
    }
}
