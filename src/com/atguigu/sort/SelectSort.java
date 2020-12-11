package com.atguigu.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
//        int[] array = {101, 34, 119, 1};
//        System.out.println("原数组:"+Arrays.toString(array));
//        selectSort(array);
        int arr2[] = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] =(int) (Math.random()*8000000) ; //生成[0,8000000)的随机数
        }
        long t1 = System.currentTimeMillis();
        selectSort(arr2);
        long t2 = System.currentTimeMillis();
        System.out.println("排序使用的时间是:"+(t2-t1)+"毫秒");
    }

    //选择排序
    public static void selectSort(int[] arr) {
        //时间复杂度O(n^2)
        //原始十足101,34,119,1
        //第一轮 1,34,119,101 找到最小的,和第1个交换位置
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                //第一个不用遍历,从第2个开始遍历数组,如果发现比最小的还要小的,就重新确定最小的位置
                if (arr[j] < min) {
                    minIndex = j; //本轮最小数的下标 3
                    min = arr[j]; //本轮最小数 1
                }
            }
            //交换arr[i]和最小值的位置
            if (minIndex != i) { //最小的就是0位置的就不用交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            //System.out.println("第" + i + "轮后:" + Arrays.toString(arr));
        }

    }


}
