package com.atguigu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建几个节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("==============================");
//        System.out.println("中序遍历");
//        binaryTree.midOrder();
//        System.out.println("==============================");
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        System.out.println("==============================");
//        System.out.println("前序查找");
//        HeroNode resNode = binaryTree.preOrderSearch(15);
//        if (resNode != null) {
//            System.out.printf("找到了,信息=%d,名字=%s", resNode.no, resNode.getName());
//        } else {
//            System.out.printf("没找到no=%d的英雄", 5);
//        }
        System.out.println("删除前:");
        binaryTree.preOrder();
        binaryTree.deleteNode(5);
        System.out.println("删除后:");
        binaryTree.preOrder();

    }

}

//定义BinaryTree
class BinaryTree {
    private HeroNode root; //根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void deleteNode(int no) {
        if (this.root == null) {
            System.out.println("二叉树为空,没有节点可以删除");
        }
        if (root.getNo() == no) { //root就是要删除的节点,整个树删除
            root = null;
        }
        root.deleteNode(no);
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode midOrderSearch(int no) {
        if (root != null) {
            return this.root.midOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//创建heroNode
class HeroNode {
    int no;
    String name;
    HeroNode left; //默认null
    HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //递归删除节点
    public void deleteNode(int no) {
        /*
         * 判断当前节点的子节点是否是需要删除的节点,
         * 左子节点不为空且就是要删除的节点this.left =null置空,并且返回结束递归
         * 右子节点不为空且就是要删除的节点this.right =null置空,并且返回结束递归
         * 上面两部没有匹配到,需要向左子树递归删除,还没有,向右子树递归删除
         * */
        if (this.getLeft() != null && this.getLeft().getNo() == no) { //左子树匹配
            this.left = null;
            return;
        }
        if (this.getRight() != null && this.getRight().getNo() == no) {
            this.right = null;
            return;
        }
        //向左子树递归
        if (this.left != null) {
            this.getLeft().deleteNode(no);
        }
        //向右子树递归
        if (this.right != null) {
            this.getRight().deleteNode(no);
        }
        System.out.println("没有找到要删除的节点");
        return;
    }

    //前序遍历
    public void preOrder() {
        //先输出父节点
        System.out.println(this);
        //再输出左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //再输出右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void midOrder() {
        //先左字数
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        //右子树
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //先左字树
        if (this.left != null) {
            this.left.postOrder();
        }
        //右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        //判断当前节点的no是否等于no
        if (this.no == no) {
            return this;
        }
        //判断左子节点是否为空
        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { //说明找到了
            return resNode;
        }
        if (this.getRight() != null) { //左边没找到,去右边找
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no) {
        //遍历左子树查找
        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().midOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //当前节点比较
        if (this.no == no) {
            return this;
        }
        //遍历右子树查找
        if (this.getRight() != null) {
            resNode = this.getRight().midOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //查找左边子树
        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //查找右边子树
        if (this.getRight() != null) {
            resNode = this.getRight().postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //和当前节点比较
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
