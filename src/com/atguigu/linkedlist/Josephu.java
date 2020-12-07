package com.atguigu.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.printCurLinkedList();
        System.out.println("出圈测试");
        circleSingleLinkedList.countBoy(1,2,5); //2,4,1,5,3
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

    //根据输入计算孩子出圈顺序

    /**
     * description
     *
     * @param startNo  从第几个小孩开始数
     * @param countNum 数几下
     * @param nums     共有多少个小孩
     * @return void
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            //数据校验
            System.out.println("参数有误!");
            return;
        }
        //创建辅助指针helper指向最后一个
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                //helper此时是最后一个节点
                break;
            }
            //helper前进一位
            helper = helper.getNext();
        }
        //报数前,让first和helper移动k-1次
        for (int j=0;j<startNo-1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //小孩报数时,让first和helper同时移动countNum-1次
        //此时出圈,直到圈中只有一个孩子
        while (true){
            //判断只剩一个孩子
            if (first == helper){
                break;
            }
            //移动first countNum-1次
            for (int j=0;j<countNum-1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时出圈
            System.out.printf("小孩%d出圈 \n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        //循环完成还有最后一个节点,也要出圈
        System.out.printf("最后的小孩%d出圈 \n",first.getNo());

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
