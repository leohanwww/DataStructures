package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.add(new HeroNode(2, "武松", "打虎"));
        singleLinkedList.add(new HeroNode(5, "林冲", "豹子头"));
        singleLinkedList.add(new HeroNode(7, "卢俊义", "玉麒麟"));
        System.out.println("当前链表为:");
        singleLinkedList.list();
        singleLinkedList.addAfter(new HeroNode(8, "吴用", "智多星"));
        System.out.println("添加2号节点后的链表为:");
        singleLinkedList.list();
        System.out.println("修改2号节点");
        singleLinkedList.update(new HeroNode(2, "张三", "好人"));
        System.out.println("修改2号节点后的链表为:");
        singleLinkedList.list();
        singleLinkedList.delete(new HeroNode(8, "吴用", "智多星"));
        System.out.println("删除8号节点后的链表为");
        singleLinkedList.list();
        System.out.println("逆序输出链表");
        reversePrint(singleLinkedList.getHead());
    }

    //获取单链表节点个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //获取单链表倒数第n个节点
    public static HeroNode getInverseNode(HeroNode head, int n) {
        if (head.next == null) {
            return null;
        }
        //获取长度
        int length = getLength(head);
        int number = length - n; //节点的顺序,第number个
        HeroNode temp = head;
        if (n <= 0 || n > length) {
            return null;
        }
        //获取第number个节点
        for (int i = 0; i < number; i++) {
            temp = temp.next;
        }
        return temp;


    }

    //将单链表反转,从原链表一个一个取node,加到reverse链表最前面,然后将原链表的head节点的next设置为reverse链表的第一个节点
    public static void reverse(HeroNode head) {
        //为空或者只有一个节点
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;//指向cur节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原链表,取出一个放到新的链表头后面
        while (cur != null) {
            next = cur.next; //先保存cur的下一个节点,为了链表不断
            cur.next = reverseHead.next; // 将新链表头和老链表结合
            reverseHead.next = cur; //将cur连接到新的链表上
            cur = next; // 将cur往后挪一个
        }
        //将原链表头取代反转的链表头
        head.next = reverseHead.next;
    }

    //单链表逆序打印,不破坏原结构
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //遍历取出node
        HeroNode cur = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null) {
            //放入栈中
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中节点打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}

//构建单链表
class SingleLinkedList {
    //初始化头节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

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
    public void addAfter(HeroNode heroNode) {
        //创建temp节点,位置位于添加节点的前一个
        HeroNode temp = head;
        boolean flag = false; //添加的位置是否存在,存在的话要报错
        while (true) { //此循环是为了找到temp的正确位置,循环结束后temp位于heronode的后面一个位置
            if (temp.next == null) {
                break; //temp已经是最后一个
            }
            if (temp.next.no > heroNode.no) {
                //找到对的位置,插入到temp后面
                break;
            } else if (temp.next.no == heroNode.no) {
                //已经存在和heroNode同样编号的节点了,提示重复
                flag = true;//编号存在
                break;
            }
            temp = temp.next;//temp后移一个位置
        }
        //判断flag
        if (flag) {
            //temp和heronode编号一样,不能插入
            System.out.println("准备插入的编号已经存在,编号:" + heroNode.no);
        } else {
            //插入到链表中,temp的后面
            //新的节点的next取代temp的next
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改节点信息
    //根据参数的no编号来修改
    public void update(HeroNode newHeroNode) {
        //判断空
        if (head.next == null) {
            return;
        }
        //找到需要修改的节点
        HeroNode temp = head;
        boolean flag = false;
        while (true) { //遍历链表
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;//temp后移一位
        }
        if (flag) {
            //找到了需要修改的节点
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            //没找到,提示
            System.out.println("找不到需要更新的节点编号:" + newHeroNode.no);
        }
    }

    //删除节点
    public void delete(HeroNode heroNode) {
        HeroNode temp = head; //制造temp节点
        boolean flag = false;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                //到最后一个节点了
                break;
            }
            if (temp.next.no == heroNode.no) {//判断条件:temp.next.next==heroNode
                //找到了要删除节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移
        }
        if (flag) {
            //删除节点
            temp.next = temp.next.next;
        } else {
            System.out.println("没找到要删除的节点:" + heroNode.no);
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            return;
        }
        //显示其余节点
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {//最后一个节点
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
