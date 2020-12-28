package com.atguigu.avl;


public class AVLTreeDemo {
    public static void main(String[] args) {
        // int arr[] = {4,3,6,5,7,8};
        //int arr[] = {10, 12, 8, 9, 7, 6};
        int arr[] = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历:");
        avlTree.infixOrder();
        System.out.println("旋之前树的高度:" + avlTree.root.height());
        System.out.println("左子树的高度:" + avlTree.root.left.height());
        System.out.println("右子树的高度:" + avlTree.root.right.height());
    }
}


class AVLTree {
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
                    } else {
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

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以当前节点为根节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    public void leftRotate() {
        Node newNode = new Node(value); //创建新的节点
        //把新节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //把新节点的右子树设置为当前节点的右子树的右子树
        newNode.right = this.right.left;
        //把当前节点的值换为右子节点的值
        this.value = this.right.value;
        //把当前节点的右子树设为右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树设置为新节点
        this.left = newNode;
    }

    //右旋转方法
    public void rightRotate() {
        Node newNode = new Node(this.value);
        //新节点的右子树-当前节点的右节点
        newNode.right = this.right;
        //新节点的左子节点设置为当前节点的左节点的右节点
        newNode.left = this.left.right;
        //当前节点值=左子节点的值
        this.value = this.left.value;
        //当前节点左子树设置成左子树的左子树
        this.left = this.left.left;
        //当前节点的右节点设置成新节点
        this.right = newNode;

    }


    /**
     * 查找要删除的节点
     * description
     *
     * @param value 要删除的节点的值
     * @return Node 找到就返回该节点
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
     * @return Node 要删除的节点的父节点
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
        //当添加完一个节点,右子树的高度>左子树高度+1,需要左旋转
        if ((rightHeight() - leftHeight()) > 1) {
            //右子树的左子树高度>右子树的右子树高度
            //先对右子树进行右旋转
            //然后再对当前节点进行左旋转
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            }else {
                //直接左旋转
                leftRotate();
            }
            return; //已经平衡了,不进行下面的判断
        }
        if ((leftHeight() - rightHeight()) > 1) {
            //先要判断,左子树的右子树高度>左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                this.left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            } else {
                //直接右旋转即可
                rightRotate();
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
