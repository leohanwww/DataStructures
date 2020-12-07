package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(new HeroNode2(1, "宋江", "及时雨"));
        doubleLinkedList.add(new HeroNode2(3, "武松", "打虎"));
        doubleLinkedList.add(new HeroNode2(5, "林冲", "豹子头"));
        doubleLinkedList.add(new HeroNode2(7, "卢俊义", "玉麒麟"));
        System.out.println("当前链表为:");
        doubleLinkedList.list();
        System.out.println("添加一个节点到最后");
        doubleLinkedList.add(new HeroNode2(9, "吴用", "智多星"));
        System.out.println("添加后链表为:");
        doubleLinkedList.list();
        System.out.println("修改列表");
        doubleLinkedList.update(new HeroNode2(3, "张三", "吃饭哥"));
        System.out.println("修改后的链表为");
        doubleLinkedList.list();
        System.out.println("删除一个节点");
        doubleLinkedList.delete(new HeroNode2(3, "张三", "吃饭哥"));
        System.out.println("删除后的链表为");
        doubleLinkedList.list();
        System.out.println("添加新节点2到1节点后面");
        doubleLinkedList.addAfter(new HeroNode2(2, "又来一个", "玩具哥"));
        System.out.println("添加了2号节点的链表是");
        doubleLinkedList.list();
        System.out.println("添加新节点11到9节点后面");
        doubleLinkedList.addAfter(new HeroNode2(11, "又来一个11", "玩具哥11"));
        System.out.println("添加了11号节点的链表是");
        doubleLinkedList.list();

    }

}

//双向链表
class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            return;
        }
        //显示其余节点
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {//最后一个节点
                break;
            }
            //输出temp
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加到双向链表最后
    public void add(HeroNode2 heroNode) {
        //使用辅助变量,指向head
        HeroNode2 temp = head;
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
        //双向链表最后一个节点的pre指向前一个
        heroNode.pre = temp;
    }

    //修改一个节点的内容
    public void update(HeroNode2 newHeroNode) {
        //判断空
        if (head.next == null) {
            return;
        }
        //找到需要修改的节点
        HeroNode2 temp = head;
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

    //删除一个节点
    //直接找到要删除的节点
    public void delete(HeroNode2 heroNode) {
        if (head.next == null) { //空链表
            System.out.println("链表为空,不能删除");
            return;
        }
        HeroNode2 temp = head.next; //制造temp节点
        boolean flag = false;
        //遍历链表,为了找到要删除的节点
        while (true) {
            if (temp == null) {
                //到最后一个节点了
                break;
            }
            if (temp.no == heroNode.no) {//判断条件:temp.next.next==heroNode
                //找到了要删除节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移
        }
        if (flag) {
            //删除节点
            temp.pre.next = temp.next; //temp前一个的next指向temp的后一个
            if (temp.next != null) {
                //如果是最后一个节点,就不需要执行下面
                temp.next.pre = temp.pre;//temp后一个节点的pre指向temp的前一个
            }
        } else {
            System.out.println("没找到要删除的节点:" + heroNode.no);
        }
    }

    //双向链表按照编号添加
    public void addAfter(HeroNode2 heroNode) {
        //创建temp节点,位置位于添加节点的前一个
        HeroNode2 temp = head.next;
        boolean flag = false; //添加的位置是否存在,存在的话要报错
        while (true) { //此循环是为了找到temp的正确位置
            if (temp == null) {
                break; //temp已经是最后一个
            }
            if (heroNode.no < temp.next.no) {
                //找到对的位置,插入到temp后面
                break;
            } else if (temp.no == heroNode.no) {
                //已经存在和heroNode同样编号的节点了,提示重复
                flag = true;//编号存在
                break;
            }
            temp = temp.next;//temp后移一个位置
        }
        //判断flag
        HeroNode2 nextNode = null;
        if (flag) {
            //temp和heronode编号一样,不能插入
            System.out.println("准备插入的编号已经存在,编号:" + heroNode.no);
        } else {
            //插入到链表中,temp的后面
            //使用变量保存temp的下一个节点
            nextNode = temp.next;
            temp.next = heroNode; //temp的next指向heronode
            heroNode.pre = temp; //heroonode的pre指向temp
            if (nextNode != null) {
                heroNode.next = nextNode;
                nextNode.pre = heroNode;
            }

        }

    }

}

//定义一个节点对象
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; //指向前一个节点

    public HeroNode2(int no, String name, String nickName) {
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
