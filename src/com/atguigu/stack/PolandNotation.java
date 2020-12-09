package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//后缀表达式计算
public class PolandNotation {
    public static void main(String[] args) {
        //(3+4) × 5 - 6     3 4 + 5 × 6 -
        String suffixExpression = "3 4 + 5 * 6 - ";
        //思路
        //先将"3 4 + 5 × 6 - "放到ArrayList中
        //将放到ArrayList中传递给一个方法,遍历ArrayList
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println("res="+res);
    }

    //将一个逆波兰表达式,依次将数据和运算符放到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    //对逆波兰表达式的运算
    //从左到右扫描 [3, 4, +, 5, ×, 6, -],将3,4压栈
    //遇到运算符弹出3,4 计算3+4=7 将7压栈,将5压栈,扫描遇到× 弹出7和5 计算7*5=35
    //压栈35,压栈6 扫到- 弹出6,35 计算 35 -6=29 压栈
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : ls) {
            //正则表达式取出数
            if (item.matches("\\d+")){//匹配的是多位数
                stack.push(item);
            }else { //匹配到运算符 item现在是运算符
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //计算两数的结果
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符错误");
                }
                //res入栈
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
