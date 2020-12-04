package com.atguigu.queue;

import java.util.Scanner;

//环形队列
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("环形队列测试");
        CircleArrayQueue queue = new CircleArrayQueue(4); //有效数据最大是3
        char key = ' ';//输入的
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加到队列");
            System.out.println("g(get): 获取元素");
            System.out.println("h(head): 显示队列头");
            key = scanner.next().charAt(0); //接收用户输入的字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数:");
                    int c = scanner.nextInt();
                    queue.addToQueue(c);
                    break;
                case 'g':
                    try {
                        int arrItem = queue.getArrItem();
                        System.out.println("取出的数据是:" + arrItem);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int i = queue.headQueue();
                        System.out.println("队列头数据:" + i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;
                default:
                    break;
            }

        }

    }
}

//使用数组模拟队列
class CircleArrayQueue {
    private final int maxSize;
    private int front; //队头
    private int rear; //队尾
    private int[] arr; //模拟队列

    //队列构造器
    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满
    public Boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public Boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addToQueue(int item) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满,不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = item;
        //将rear后移,考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列数据
    public int getArrItem() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }
        //分析front是指向队列的第一个元素
        //取出front处的值
        int value = arr[front];
        //将front增加
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列没有数据");
            return;
        }
        //思路,从front开始,遍历到rear
        for (int i = front; i < (front + getValid()); i++) {
            System.out.printf(String.valueOf(arr[(i%maxSize)]) + " ");
        }
    }

    //求出队列中有效数据的个数
    public int getValid() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头部,不取出头部
    public int headQueue() {
        //判断不为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");

        }
        return arr[front];
    }
}
