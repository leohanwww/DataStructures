package com.atguigu.sort;

import java.util.Arrays;

//希尔排序
public class ShellSort {
    public static void main(String[] args) {
//        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);
//        System.out.println("处理完成后数组是:" + Arrays.toString(arr));
        int arr2[] = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成[0,8000000)的随机数
        }
        long t1 = System.currentTimeMillis();
        shellSort2(arr2);
        long t2 = System.currentTimeMillis();
        System.out.println("排序使用的时间是:" + (t2 - t1) + "毫秒");

    }

    //逐步推导
    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int len = arr.length / 2; len > 0; len /= 2) { //分几组由数组长度决定
            for (int i = len; i < arr.length; i++) {
                //内循环遍历各组中所有的元素,5组,每组2个元素
                for (int j = i - len; j >= 0; j -= len) {
                    //本组元素大于加上步长的那个元素,说明需要交换
                    if (arr[j] > arr[j + len]) {
                        temp = arr[j];
                        arr[j] = arr[j + len];
                        arr[j + len] = temp;
                    }
                }
            }
        }

  /*    //希尔排序第一轮
        //第一轮排序,将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            //内循环遍历各组中所有的元素,5组,每组2个元素
            for (int j = i - 5; j >= 0; j -= 5) {
                //本组元素大于加上步长的那个元素,说明需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮处理完成后数组是:"+ Arrays.toString(arr));

        //希尔排序第二轮
        //第二轮排序,5/2=2分成2组
        for (int i = 2; i < arr.length; i++) {
            //内循环遍历各组中所有的元素
            for (int j = i - 2; j >= 0; j -= 2) {
                //本组元素大于加上步长的那个元素,说明需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二轮处理完成后数组是:"+ Arrays.toString(arr));


        //希尔排序第三轮
        //第三轮排序,2/2=1分成1组
        for (int i = 1; i < arr.length; i++) {
            //内循环遍历各组中所有的元素,
            for (int j = i - 1; j >= 0; j -= 1) {
                //本组元素大于加上步长的那个元素,说明需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三轮处理完成后数组是:"+ Arrays.toString(arr));
*/
    }

    //对交换式shell排序改为移位法
    public static void shellSort2(int[] arr) {
        for (int len = arr.length / 2; len > 0; len /= 2) { //分几组由数组长度决定
            //从len个元素开始,诸葛对其所在的组进行直接插入排序
            for (int i = len; i < arr.length; i++) {
                int j = i;//待插入的位置
                int temp = arr[j]; //待插入的数字
                if (arr[j] < arr[j - len]) {
                    while (j - len >= 0 && temp < arr[j - len]) {
                        //移动
                        arr[j] = arr[j - len];
                        j -= len;
                    }
                    //当退出while循环,就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }

    }
}
