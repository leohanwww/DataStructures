package com.atguigu.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("二叉排序树中序遍历：");
        binarySortTree.infixOrder();
    }
}

//二叉排序树
class BinarySortTree{
    Node root;

    public void add(Node node){
        if (root==null){
            root = node; //root为空的情况
        }else {
            root.addNode(node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if (root==null){
            System.out.println("树为空，无法遍历");
            return;
        }
        root.infixOrder();
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点
    public void addNode(Node n) {
        if (n == null) {
            return;
        }
        //比较value大小,递归添加
        if (n.value > this.value) {
            if (this.right == null) {
                this.right = n;
            } else {
                //值大，添在右边
                this.right.addNode(n);
            }
        } else {
            if (this.left == null) {
                this.left = n;
            } else {
                //值大，添在右边
                this.left.addNode(n);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        //左递归遍历
        if (this.left!=null){
            this.left.infixOrder();
        }
        //中
        System.out.println(this);
        //右
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
