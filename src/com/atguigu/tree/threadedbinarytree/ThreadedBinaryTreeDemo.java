package com.atguigu.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //中序线索化二叉树
        //创建几个节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(3, "吴用");
        HeroNode node3 = new HeroNode(6, "卢俊义");
        HeroNode node4 = new HeroNode(8, "林冲");
        HeroNode node5 = new HeroNode(10, "关胜");
        HeroNode node6 = new HeroNode(14, "鲁智深");
        //创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试中序线索化,找no10这个点,看left leftType right righType
        HeroNode leftNode = node5.getLeft();
        System.out.println("node5的id:"+node5.getNo()+"node5的前驱节点是:"+leftNode);
        HeroNode rightNode = node5.getRight();
        System.out.println("node5的id:"+node5.getNo()+"node5的后继节点是:"+rightNode);
        System.out.println("使用线索化的方式遍历线索化后的二叉树");
        threadedBinaryTree.threadedList();
    }
}

//定义BinaryTree,实现线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root; //根节点

    //为了实现线索化,需要创建当前节点的前驱节点的指针
    //递归线索化时,pre总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历中序线索化二叉树
    public void threadedList(){
        //定义变量,存储当前变量的节点
        HeroNode node = root;
        while (node!=null){
            //循环变量leftType==1的节点,第一个找到的是8节点
            while (node.getLeftType()==0){ //一直找leftType==1的节点
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //当前节点右指针指向后继节点,就一直输出
            if (node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的节点
            node = node.getRight();
        }
    }

    /**
     * description  二叉树中序线索化
     *
     * @param node 当前需要线索化的节点
     * @return void
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        if (node.left == null) {
            node.setLeft(pre); //8节点的pre是空
            node.setLeftType(1); //前驱节点
        }
        //当处理3号节点时,此时3号节点在8号节点后面一个处理
        if (pre != null && pre.getRight() == null) { //pre此时是8号节点
            pre.setRight(node);
            pre.setRightType(1); //8节点 set 3节点是它的后继节点
        }
        //每处理一个节点后,让当前节点是下一个节点的前驱节点
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());

    }

    //删除节点
    public void deleteNode(int no) {
        if (root == null) {
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

    //leftType=0表示指向左子树,=1表示指向前驱节点
    private int leftType;
    //rightType=0表示指向右子树,=1表示指向后继节点
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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
