package com.atguigu.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1,"宋江","及时雨"));
        singleLinkedList.add(new HeroNode(2,"武松","打虎"));
        singleLinkedList.add(new HeroNode(3,"林冲","豹子头"));
        singleLinkedList.add(new HeroNode(4,"卢俊义","玉麒麟"));
        singleLinkedList.list();
    }

}

//构建单链表
class SingleLinkedList {
    //初始化头节点
    private HeroNode head = new HeroNode(0, "", "");

    //添加到链表的最后
    //找到当前链表的最后节点
    //将最后节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //使用辅助变量,指向head
        HeroNode temp = head;
        //遍历链表找到最后节点
        while (true) {
            if (temp.next == null) {//找打了最后一个节点
                break;
            }
            //往后走一个节点
            temp = temp.next;
        }
        //添加到最后一个节点的后面
        temp.next = heroNode;
    }

    //显示链表
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            return;
        }
        //显示其余节点
        HeroNode temp = head.next;
        while (true){
            if (temp==null){//最后一个节点
                break;
            }
            //输出temp
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//定义一个节点对象
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
