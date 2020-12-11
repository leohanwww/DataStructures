package com.atguigu.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int array[] = {3, 9, -1, 10, -2};

        //第一趟排序将最大的排在最后
        int temp = 0;
/*        for (int j = 0; j < array.length; j++) {
            //如果前面的数比后面的数大,就交换
            if (array[j] > array[j + 1]) {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }*/
        //冒泡排序时间复杂度O(n^2)
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                //如果前面的数比后面的数大,就交换
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(array));

        }
    }
}
