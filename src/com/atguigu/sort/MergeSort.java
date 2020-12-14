package com.atguigu.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
 //       int[] temp = new int[arr.length];

//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println("归并排序后" + Arrays.toString(arr));
        int arr2[] = new int[80000];
        int[] temp = new int[arr2.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成[0,8000000)的随机数
        }
        long t1 = System.currentTimeMillis();
        mergeSort(arr2,0,arr2.length-1,temp);
        long t2 = System.currentTimeMillis();
        System.out.println("排序使用的时间是:" + (t2 - t1) + "毫秒");
    }

    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //左递归分解
            mergeSort(arr, left, mid, temp);
            //右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * description 合并的方法
     *
     * @param arr   待排序数组
     * @param left  左边有序序列初始索引
     * @param mid   中间索引
     * @param right 右边有序序列初始索引
     * @param temp  做中转的数组
     * @return void
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //左边有序数组的初始索引
        int j = mid + 1; //右边有序数组的初始索引
        int t = 0; //temp数组的开始索引
        //把左右两边的数据按照规则填充到temp
        //直到有一边的有序序列处理完毕
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                //左边小,temp填充左边,i增加
                temp[t] = arr[i];
                i += 1;
                t++;
            } else {
                //右边小,temp填充右边,j增加
                temp[t] = arr[j];
                j += 1;
                t++;
            }
        }

        //把有剩余的有序序列拷贝到temp
        while (i <= mid) { //先拷贝小的一边的
            //左边还有剩余元素
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            //右边还有剩余元素
            temp[t] = arr[j];
            t++;
            j++;
        }
        //将temp拷贝覆盖原数组
        //不是每次都拷贝全部temp
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            //第一次合并tempLeft=0, right=1, 第二次合并tempLeft=2,right=3
            //第三次合并0 3 第四次合并 0 7
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
