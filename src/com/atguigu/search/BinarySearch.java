package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

//二分查找,数组有序的条件下
public class BinarySearch {
    public static void main(String[] args) {
/*        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int findIndex = binarySearch(arr, 0, arr.length - 1, 897);
        if (findIndex != -1) {
            System.out.println("找到的数位置是:" + findIndex);
        } else {
            System.out.println("找不到这个数");
        }*/
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000,1000, 1234};
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1100);
        System.out.println("找到的下标是:" + integers);
    }

    /**
     * description
     *
     * @param arr     有序数组
     * @param left    左起始下标
     * @param right   右起始下标
     * @param findVal 需要找到的数
     * @return int 找到的下标
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //假如left>right,说明递归整个数组也没有找到,返回-1
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        //假如要找的值大于中间值
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        }
        //假如要找的值小于中间值
        else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    //找到全部相同的值
    //在找到mid索引后,不要马上返回,
    //向mid左边扫描,找到所有和arr[mid]一样的数,加入数组ArrayList
    //向mid右边扫描,找到所有和arr[mid]一样的数,加入数组ArrayList
    //将ArrayList返回
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //假如left>right,说明递归整个数组也没有找到,返回-1
        if (left > right) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(-1);
            return arrayList;
        }
        //确定中间数
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        ArrayList<Integer> list = new ArrayList<>();
        //假如要找的值大于中间值
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        }
        //假如要找的值小于中间值
        else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //向mid左边找和arr[mid]一样的数,加入ArrayList
            int temp_left = mid - 1;
            while (temp_left >= 0 && (arr[temp_left] == findVal)) { //下标不能小于0且值 相等
                //加入到ArrayList中
                list.add(temp_left);
                temp_left--;
            }
            list.add(mid); //把找到的中间点加入
            //向右扫描
            int temp_right = mid + 1;
            while (temp_right <= right && (arr[temp_right] == findVal)) {
                //加入到ArrayList中
                list.add(temp_right);
                temp_right++;
            }
            return list;
        }
    }

}
