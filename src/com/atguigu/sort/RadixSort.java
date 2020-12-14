package com.atguigu.sort;


import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
/*        int arr2[] = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成[0,8000000)的随机数
        }
        long t1 = System.currentTimeMillis();
        radixSort(arr2);
        long t2 = System.currentTimeMillis();
        System.out.println("排序使用的时间是:" + (t2 - t1) + "毫秒");*/
    }

    //基数排序
    public static void radixSort(int[] arr) {

        //第一轮,个位数排到桶里
        //定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //定义一个一维数组记录每个桶放了几个数据
        int[] bucketElementCounts = new int[10];
        //bucketElementCounts[0]记录的就是bucket[0]里面的元素个数

        //找到arr里最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>max){
                max = arr[i];
            }
        }
        //max的位数
        int maxLengh = (max+"").length();

        for (int i = 0, n = 1; i < maxLengh; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //针对每一位进行排序,第一次个位数,第二次十位数,第三次百位数
                int digitOgElement = arr[j] / n % 10;
                //放入到对应的桶中
                //[0][1] 对应个位数为0的第1个数 bucketElementCounts统计出几个个位数为0的数
                bucket[digitOgElement][bucketElementCounts[digitOgElement]] = arr[j];
                bucketElementCounts[digitOgElement]++;
            }
            //第一轮从桶中取出数放到arr
            int index = 0;
            //遍历每一个桶,并将数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据,才取出
                if (bucketElementCounts[k] != 0) {
                    //循环第k个一维数组,放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                    //每一轮后,需要将bucketElementCounts[k] =0
                    bucketElementCounts[k] = 0;
                }
            }
           // System.out.println("第"+(i+1)+"轮,对个位的排序处理," + Arrays.toString(arr));

        }

 /*       //第一轮 {53, 3, 542, 748, 14, 214}
        for (int j = 0; j < arr.length; j++) {
            //第一轮,放个位数,取出每个元素的个位
            int digitOgElement = arr[j] /1 % 10;
            //放入到对应的桶中
            //[0][1] 对应个位数为0的第1个数 bucketElementCounts统计出几个个位数为0的数
            bucket[digitOgElement][bucketElementCounts[digitOgElement]] = arr[j];
            bucketElementCounts[digitOgElement]++;
        }
        //第一轮从桶中取出数放到arr
        int index = 0;
        //遍历每一个桶,并将数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才取出
            if (bucketElementCounts[k] != 0) {
                //循环第k个一维数组,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
                //每一轮后,需要将bucketElementCounts[k] =0
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第一轮,对个位的排序处理," + Arrays.toString(arr));

        //第二轮
        for (int j = 0; j < arr.length; j++) {
            //第二轮,放个位数,取出每个元素的个位
            int digitOgElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            //[0][1] 对应个位数为0的第1个数 bucketElementCounts统计出几个个位数为0的数
            bucket[digitOgElement][bucketElementCounts[digitOgElement]] = arr[j];
            bucketElementCounts[digitOgElement]++;
        }
        //第二轮从桶中取出数放到arr
        index = 0;
        //遍历每一个桶,并将数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才取出
            if (bucketElementCounts[k] != 0) {
                //循环第k个一维数组,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //每一轮后,需要将bucketElementCounts[k] =0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮,对十位的排序处理," + Arrays.toString(arr));


        //第三轮
        for (int j = 0; j < arr.length; j++) {
            //第二轮,放个位数,取出每个元素的个位
            int digitOgElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            //[0][1] 对应个位数为0的第1个数 bucketElementCounts统计出几个个位数为0的数
            bucket[digitOgElement][bucketElementCounts[digitOgElement]] = arr[j];
            bucketElementCounts[digitOgElement]++;
        }
        //第二轮从桶中取出数放到arr
        index = 0;
        //遍历每一个桶,并将数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才取出
            if (bucketElementCounts[k] != 0) {
                //循环第k个一维数组,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //每一轮后,需要将bucketElementCounts[k] =0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮,对百位的排序处理," + Arrays.toString(arr));*/
    }
}
