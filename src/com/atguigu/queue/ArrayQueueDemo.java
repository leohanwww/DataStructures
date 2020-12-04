package com.atguigu.queue;

import java.util.Scanner;

//模拟队列 先进先出
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
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
                    break;
                default:
                    break;
            }
            System.out.println("程序退出");
        }

    }
}

//使用数组模拟队列
class ArrayQueue {
    private final int maxSize;
    private int front; //队头
    private int rear; //队尾
    private int[] arr; //模拟队列

    //队列构造器
    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //判断队列是否满
    public Boolean isFull() {
        return rear == maxSize - 1;
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
        }
        rear++; //队尾后移一个
        arr[rear] = item; //插入数据
    }

    //获取队列数据
    public int getArrItem() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }
        //从队尾取
        front++;
        return arr[front];
    }

    //显示队列数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(String.valueOf(arr[i]) + " ");
        }
    }

    //显示队列头部,不取出头部
    public int headQueue() {
        //判断不为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");

        }
        return arr[front + 1];
    }
}
