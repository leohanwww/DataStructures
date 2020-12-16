package com.atguigu.hashtable;

import javax.sound.midi.SoundbankResource;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        hashTable.add(new Emp(1, "张三"));
        hashTable.add(new Emp(2, "李四"));
        hashTable.add(new Emp(3, "王二"));
        hashTable.add(new Emp(3, "王二"));
        hashTable.list();
        hashTable.findEmpById(1);
    }
}

//管理多条hash表
class HashTable {
    private EmpLinkedList[] empLinkedLists;
    int size; //共有多少条链表

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //大坑啊,分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }

    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工id,判断添加的链表
        int empLinkedListNo = hashFun(emp.getId());
        //将雇员添加到对应的链表
        empLinkedLists[empLinkedListNo].add(emp);
        System.out.println();
    }

    public void list() {
        if (empLinkedLists.equals(null)) {
            System.out.println("哈希表为空");
        }
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void findEmpById(int id) {
        int hashFun = hashFun(id);
        Emp emp = empLinkedLists[hashFun].findEmpById(id);
        if (emp != null) {
            System.out.println("在第" + hashFun + "条哈希表中找到该雇员" + "id==" + id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //散列函数,取模法
    public int hashFun(int id) {
        return id % size;
    }
}

class Emp {
    int id;
    String name;
    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//链表
class EmpLinkedList {
    private Emp head; //默认null

    //添加雇员到链表
    //添加到链表的最后
    public void add(Emp emp) {
        //如果是添加第一条雇员
        if (head == null) {
            head = emp;
            return;
        }
        //不是第一个雇员,则添加辅助指针指向后一个雇员
        Emp cur = head;
        while (true) {
            if (cur.next == null) {
                //找到最后一个雇员
                break;
            }
            cur = cur.next; //往下移动一位
        }
        cur.next = emp; //添加到最后
    }

    //遍历链表
    public void list(int no) {
        //使用辅助指针遍历
        if (head == null) {
            System.out.println("第" + no + "链表为空");
            return;
        }
        System.out.printf("第" + no + "链表为:");
        //循环打印链表
        Emp cur = head;
        while (cur != null) {
            System.out.println(cur.toString());
            cur = cur.next;
        }
    }

    //查找雇员
    public Emp findEmpById(int id) {
        if (head.equals(null)) {
            System.out.println("链表为空");
            return null;
        }
        Emp cur = head;
        while (true) {
            if (cur.id == id) {
                break;
            }
            if (cur.next == null) {
                cur = null;
                break;
            }
            cur = cur.next;
        }
        return cur;


    }
}

