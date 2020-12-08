package com.atguigu.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        boolean loop = true;
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("s:显示栈");
            System.out.println("p:加入一个数据");
            System.out.println("o:取出一个数据");
            System.out.println("q:退出");
            System.out.println("请输入选择");
            key = scanner.next();
            switch (key){
                case "s":
                    try {
                        stack.list();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());;
                    }
                    break;
                case "p":
                    System.out.println("请输入要添加的数字");
                    int num  = scanner.nextInt();
                    stack.push(num);
                    break;
                case "o":
                    try {
                        int value = stack.pop();
                        System.out.println("取出的数字是:"+value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "q":
                    scanner.close();
                    loop = false;
                    break;
                default:break;
            }
        }
        System.out.println("程序退出");

    }

}

//数组模拟栈
class ArrayStack {
    private int maxSize;  //栈的最大的大小
    private int[] stack;
    private int top = -1; //栈顶,初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int num){
        //判断栈满
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        //加入数组中
        top++;
        stack[top] = num;
    }
    //出栈
    public int pop(){
        //判断栈空
        if (isEmpty()){
            throw new RuntimeException("栈为空,没有数值");
        }
        int num = stack[top];
        top--;
        return num;
    }
    //显示栈
    public void list(){
        //判断栈空
        if (isEmpty()){
            throw new RuntimeException("栈为空,没有数值");
        }
        //从栈顶开始遍历
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d \n",i,stack[i]);
        }


    }
}

