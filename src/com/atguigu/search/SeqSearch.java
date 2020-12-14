package com.atguigu.search;

public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        int index = seqSearch(arr, 34);
        if (index==-1){
            System.out.println("没有找到");
        }
        System.out.println("找到对应的数组位置:"+index);
    }


    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
