package com.atguigu.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1,"宋江","及时雨"));
        singleLinkedList.add(new HeroNode(3,"武松","打虎"));
        singleLinkedList.add(new HeroNode(5,"林冲","豹子头"));
        singleLinkedList.add(new HeroNode(7,"卢俊义","玉麒麟"));
        System.out.println("当前链表为:");
        singleLinkedList.list();
        singleLinkedList.addAfter(new HeroNode(2,"吴用","智多星"));
        System.out.println("添加2号节点后的链表为:");
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

    //根据排名将英雄插入到指定位置,如果有当前的位置存在,提示添加失败
    //参数是要加进来的node,temp是当前node
    public void addAfter(HeroNode heroNode){
        //创建temp节点,位置位于添加节点的前一个
        HeroNode temp = head;
        boolean flag = false; //添加的位置是否存在,存在的话要报错
        while (true){ //此循环是为了找到temp的正确位置,循环结束后temp位于heronode的后面一个位置
            if (temp.next == null){
                break; //temp已经是最后一个
            }
            if (temp.next.no > heroNode.no){
                //找到对的位置,插入到temp后面
                break;
            }else if (temp.next.no == heroNode.no){
                //已经存在和heroNode同样编号的节点了,提示重复
                flag = true;//编号存在
                break;
            }
            temp=temp.next;//temp后移一个位置
        }
        //判断flag
        if (flag){
            //temp和heronode编号一样,不能插入
            System.out.println("准备插入的编号已经存在,编号:"+heroNode.no);
        }else {
            //插入到链表中,temp的后面
            //新的节点的next取代temp的next
            heroNode.next = temp.next;
            temp.next = heroNode;
        }



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
