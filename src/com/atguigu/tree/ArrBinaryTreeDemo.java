package com.atguigu.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

class ArrBinaryTree {
    private int arr[]; //存储数据节点的数组

    public ArrBinaryTree(int arr[]) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }

    //顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,无法按照二叉树顺序遍历");
        }
        //先输出当前元素
        System.out.printf(arr[index]+",");
        //左序遍历
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //右序递归
        if ((2 * index) + 1 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
