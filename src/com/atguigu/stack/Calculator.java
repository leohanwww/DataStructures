package com.atguigu.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "300+2*6-2"; //表达式
        //创建数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;//扫描索引
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到的运算符保存到ch
        String keepNum = ""; //用于拼接多位数
        //扫描表达式,区分数和运算符
        for (int i = 0; i < expression.length(); i++) {
            ch = expression.charAt(i); //获得一个字符
            //判断数字或运算符
            if (operStack.isOper(ch)) {
                //是符号
                if (operStack.isEmpty()) {//判断是不是第一个符号
                    //是第一个,加入符号
                    operStack.push(ch);
                } else { //不是第一个符号
                    //获得符号栈顶的符号,不出栈
                    int ch2 = operStack.peek();
                    //比较两个符号优先级
                    if (operStack.priority(ch) <= operStack.priority(ch2)) {
                        //数栈取出两个数
                        int pop1 = numStack.pop();
                        int pop2 = numStack.pop();
                        //符号栈取出一个运算符
                        char op = (char) operStack.pop();
                        //计算结果
                        int result = numStack.cal(pop1, pop2, op);
                        //结果result入数栈
                        numStack.push(result);
                        //当前操作符ch2入符号栈
                        operStack.push(ch);
                    } else {
                        //当前操作符大于栈内第一个操作符,直接入符号栈
                        operStack.push(ch);
                    }
                }
            } else {
                //不是符号,是数字,直接入数栈
                //numStack.push(ch - 48); //根据字符的ASCII表获得数字值
                //处理2位数及以上的情况
                //处理数时,向后一位看一位,如果是数,继续向后看一位,如果是符号,就入栈这个数
                //定义一个字符串变量凭接2位以上的数字
                keepNum += ch;
                if (index == expression.length() - 1) {
                    //如果ch已经是exp的最后一位,直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字,如果是数字,继续扫描,如果是运算符,则入栈数字
                    //从i+1开始变量exp,遇到符号暂停
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //是符号,把前面的数字入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要!!!,清空keepNum
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index>=expression.length()){
                break;
            }
        }
        //表达式扫描完成,顺序从数栈和符号栈pop出,运行
        while (true) {
            //符号栈为空,则计算结束,数栈中只有一个结果
            if (operStack.isEmpty()) {
                break;
            }
            //数栈取出两个数
            int pop1 = numStack.pop();
            int pop2 = numStack.pop();
            //符号栈取出一个运算符
            char op = (char) operStack.pop();
            //计算结果
            int result = numStack.cal(pop1, pop2, op);
            numStack.push(result);
        }
        //将数栈的唯一值输出,就是结果
        System.out.printf("表达式%s = %d:", expression, numStack.pop());
    }
}

//创建栈模型,使用前面的
class ArrayStack2 {
    private int maxSize;  //栈的最大的大小
    private int[] stack;
    private int top = -1; //栈顶,初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int num) {
        //判断栈满
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        //加入数组中
        top++;
        stack[top] = num;
    }

    //获得栈顶的一个值,但不出栈
    public char peek() {
        return (char) stack[top];
    }

    //出栈
    public int pop() {
        //判断栈空
        if (isEmpty()) {
            throw new RuntimeException("栈为空,没有数值");
        }
        int num = stack[top];
        top--;
        return num;
    }

    //显示栈
    public void list() {
        //判断栈空
        if (isEmpty()) {
            throw new RuntimeException("栈为空,没有数值");
        }
        //从栈顶开始遍历
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d \n", i, stack[i]);
        }
    }

    //运算符优先级判断,使用数字表示,大的优先级高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {//不是运算符的情况
            return -1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char val) {
        if (val == '+' || val == '-' || val == '*' || val == '/') {
            return true;
        } else {
            return false;
        }
    }

    //计算方法,char和int可以混用
    public int cal(int num1, int num2, char oper) {
        int res = 0; //存放计算结果
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

}
