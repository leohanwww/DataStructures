package com.atguigu.sort;


public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println("arr=" + Arrays.toString(arr));
        int arr2[] = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成[0,8000000)的随机数
        }
        long t1 = System.currentTimeMillis();
        quickSort(arr2,0,arr2.length-1);
        long t2 = System.currentTimeMillis();
        System.out.println("排序使用的时间是:" + (t2 - t1) + "毫秒");

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左边下标
        int r = right; //右边下标
        int temp = 0;
        //中轴
        int pivot = arr[(left + right) / 2];
        //循环目的是让比pivot小的值放到pivot的左边,比pivot大的放到右边
        while (l < r) {
            //在pivot的左边一直找大于pivot的数,才退出
            while (arr[l] < pivot) { //小的话就向右找到大于中轴数的
                l += 1; //l停止在第一个找到的大于中轴的数
            }
            //在pivot的右边一直找小于pivot的数,才退出
            while (arr[r] > pivot) {
                r -= 1; //r停止在倒数第一个靠近中轴却小于中轴的数
            }
            if (l >= r) {
                //达到这样的条件说明左边的值全部小于pivot,右边的值全部大于pivot
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                //交换完成后,发现arr[l]和pivot相等,r前移一个,继续进行前面的判断
                r -= 1;
            }
            if (arr[r] == pivot) {
                //交换完成后,发现arr[r]和pivot相等,l后移一个,继续进行前面的判断
                l += 1;
            }
        }
        //如果l==r 必须l++,r--,否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //中轴左边的递归
        if (left<r){
            quickSort(arr,left,r);
        }
        //中轴右边的递归
        if (right>l){
            quickSort(arr,l,right);
        }
    }
}
