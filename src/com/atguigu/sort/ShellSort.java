package com.atguigu.sort;

import java.util.Arrays;

//希尔排序
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println("处理完成后数组是:"+ Arrays.toString(arr));

    }

    //逐步推导
    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int len = arr.length/2;len>0;len/=2){
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
}
