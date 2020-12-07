package com.atguigu.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.printCurLinkedList();
    }
}

//环形单向链表
class CircleSingleLinkedList {
    //创建first节点,first节点是不动的
    private Boy first = null;

    //添加小孩节点,构建环形链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("添加后续节点数不能小于1");
            return;
        }
        //创建辅助指针
        Boy curBoy = null;
        //循环创建列表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩节点
            if (i == 1) {
                first = boy; //头节点指向第一个小孩节点
                first.setNext(first); //自己形成环
                curBoy = first; //让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy); //cur指向新的节点
                boy.setNext(first); //新的节点指向头节点
                curBoy = boy; //cur后移
            }
        }

    }

    //遍历当前环形链表
    public void printCurLinkedList() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("当前链表为空");
            return;
        }
        //创建辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("子节点编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext(); //curBoy后移一位
        }

    }

}

//节点
class Boy {
    private int no; //编号
    private Boy next; //指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
