package com.atguigu.tree;

import java.util.Arrays;

public class heapSort {
    public static void main(String[] args) {
        //升序采用大顶堆
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
//        System.out.println("堆排序");
//        adjustHeap(arr,1, arr.length); //第一次调整后:[4, 9, 8, 5, 6]
//        System.out.println("第一次调整后:"+ Arrays.toString(arr));
//        adjustHeap(arr,0, arr.length); //第一次调整后:[9,6,8,5,4]
//        System.out.println("第二次调整后:"+ Arrays.toString(arr));
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length); //9 6 8 5 4
        }
        //交换堆顶和末尾元素
        for (int j = arr.length-1;j>0;j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //重新调整结构,以arr[0]为顶点的树,对j个元素进行调整
            adjustHeap(arr, 0, j);
        }
        System.out.println("调整后:"+ Arrays.toString(arr));
    }

    /**
     * 将以arr[i]为父节点的子树调整成arr[i]最大
     * description
     *
     * @param arr    原数组
     * @param i      表示非叶子节点数组中索引
     * @param length 对多少个元素进行调整,是在逐渐减少的
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; //取出当前的arr[i],保存
        //k起始是i的左子节点,k步长是往自己的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //找到最大值
            if (k + 1 < length && arr[k] < arr[k + 1]) { //左子节点小于右子节点的值
                k++; //k指向右子节点
            }
            if (arr[k] > temp) { //子节点大于父节点
                arr[i] = arr[k]; //把较大的值赋给当前节点
                i = k; //让i指向k,继续循环比较
            } else {
                break;
            }
            //for循环结束,以i为父节点的树的最大值,放在了最顶部
            arr[i] = temp; //将temp放到调整后的位置
        }
    }
}
