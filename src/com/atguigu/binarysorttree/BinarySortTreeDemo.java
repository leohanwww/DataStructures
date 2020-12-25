package com.atguigu.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("二叉排序树中序遍历：");
        binarySortTree.infixOrder();
        //测试删除节点
        binarySortTree.deleteNode(10);
        System.out.println("删除节点后二叉排序树中序遍历：");
        binarySortTree.infixOrder();
    }
}

//二叉排序树
class BinarySortTree {
    Node root;

    public Node getRoot() {
        return root;
    }

    //查找节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 删除以node为根节点的二叉排序树的最小节点
     * description
     *
     * @param node 传入的节点（当作二叉排序树的根节点）
     * @return int 以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node temp = node;
        //循环查找左节点，找到最小的值
        while (temp.left != null) {
            temp = temp.left;
        }
        //此时temp指向最小节点
        //删除最小的节点
        deleteNode(temp.value);
        return temp.value;
    }

    //删除节点
    public void deleteNode(int value) {
        if (root == null) {
            System.out.println("无法删除，二叉树为空");
            return;
        } else {
            Node targetNode = search(value); //找到要删除的节点
            if (targetNode == null) {
                System.out.println("找不到要删除的节点");
                return;
            }
            if (root.left == null && root.right == null) { //要删除的节点没有父节点
                root = null;
                return;
            }
            Node parent = searchParent(value); //找到targetNode的父节点
            if (targetNode.left == null && targetNode.right == null) { //targetNode是叶子节点
                if (parent.left != null && parent.left.value == value) { //target是父节点的左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { //targetNode是有两个叶子的节点
                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            } else { //targetNode是有一个子树的节点
                //targetNode有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        //targetNode有右子节点
                        //如果是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    //添加节点
    public void add(Node node) {
        if (root == null) {
            root = node; //root为空的情况
        } else {
            root.addNode(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
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


    /**
     * 查找要删除的节点
     * description
     *
     * @param value 要删除的节点的值
     * @return com.atguigu.binarysorttree.Node 找到就返回该节点
     */
    public Node search(int value) {
        if (this.value == value) { //就是该节点
            return this;
        } else if (value < this.value) { //向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * description
     *
     * @param value 子节点的值
     * @return com.atguigu.binarysorttree.Node 要删除的节点的父节点
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) { //表示这个节点就是父节点
            return this;
        } else {
            //递归查找
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else { //没有找到
                return null;
            }
        }
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
    public void infixOrder() {
        //左递归遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //中
        System.out.println(this);
        //右
        if (this.right != null) {
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
