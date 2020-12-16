package com.atguigu.search;

import java.util.Arrays;

//插值查找
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("index="+index);
    }

    /**
     * description 插值查找算法
     *
     * @param arr     数组
     * @param left    左起始
     * @param right   右终结
     * @param findVal 查找值
     * @return int
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[left] || findVal > arr[right]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal < midVal) { //左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) { //右递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }

    }
}
