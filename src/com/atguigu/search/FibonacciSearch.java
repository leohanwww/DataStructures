package com.atguigu.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
//        int[] fib = fib();
//        System.out.println(Arrays.toString(fib));
        System.out.println(fibSearch(arr,1234));
    }

    //获取斐波那契数列
    public static int[] fib() {
        int maxSize = 20;
        int[] a = new int[maxSize];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a;
    }

    /**
     * mid = low+f[k-1]-1
     * description
     * @param arr	数组
     * @param key	需要查找的值
     * @return int  对应的下标
    */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //斐波那契数的下标
        int mid = 0;
        int[] f = fib();
        while (high > f[k] - 1) { //获取斐波那契数列的数值下标
            k++;  //k=5 f[k]=8 需要有8个数据的temp
        }
        //f[k]对应的值会大于数组的长度,需要拷贝数组,arr不足f[k]长度的使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]); //f[k]=8 需要将arr扩招长度到8个元素
        //temp后面填充arr的最后一个值
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high]; //temp={1, 8, 10, 89, 1000, 1234,1234,1234}
        }
        //查找key的位置
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//向左查找
                high = mid - 1;
                //继续向前找mid=f[k-1]-1,继续找到前面的mid值
                k--;
            } else if (key > temp[mid]) {//向右查找
                low = mid + 1;
                //在mid=low+f[k-2-1]-1前面继续查找
                //f[k] = f[k-1]+f[k-2] 因为mid后面有f[k-2]个元素,f[k-2]=f[k-3]+f[k-4]
                k -= 2;
            } else { //找到
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
