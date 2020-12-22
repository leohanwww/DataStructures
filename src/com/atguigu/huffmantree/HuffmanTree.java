package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //前序遍历方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空,不能遍历");
        }
    }


    /**
     *
     * description 创建赫夫曼树的方法
     * @param arr	需要创建树的数组
     * @return com.atguigu.huffmantree.Node 创建好后树的根节点
    */
    public static Node createHuffmanTree(int[] arr) {
        //遍历arr,创建node,放入ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while (nodes.size() > 1) {
            //排序AL
            Collections.sort(nodes);
            //System.out.println(nodes);

            //取出权值最小的前2个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            //移除left right,加入parent
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}


//节点类
class Node implements Comparable<Node> {
    int value; //节点权值
    Node left; //指向左子节点
    Node right; //指向右子节点

    //前序遍历所以节点
    public void preOrder() {
        //输出当前节点
        System.out.println(this);
        //左递归遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //右递归遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        //从小到大进行排序
        return this.value - node.value;
    }
}
