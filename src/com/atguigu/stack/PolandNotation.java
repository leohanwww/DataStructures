package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//后缀表达式计算
public class PolandNotation {
    public static void main(String[] args) {
        //先将字符串转为list 1+((2+3)×4)-5 => [1 2 3 + 4 × + 5 -]
        String expression = "1+((2+3)×4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        //System.out.println("转换中缀表达式:"+toInfixExpressionList(expression));
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式:"+parseSuffixExpressionList);
        System.out.printf("expression=%d",calculate(parseSuffixExpressionList));

      /*  //(3+4) × 5 - 6     3 4 + 5 × 6 -
        String suffixExpression = "3 4 + 5 * 6 - ";
        //思路
        //先将"3 4 + 5 × 6 - "放到ArrayList中
        //将放到ArrayList中传递给一个方法,遍历ArrayList
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println("res=" + res);*/
    }

    //将中缀表达式转化为后缀表达式[1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5] =>ArrayList[1,2,3,+,4,*,+,5,-]
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
        //s2栈在转换过程中没有pop操作,后期还需要逆序输出,使用list<string> s2
        List<String> s2 = new ArrayList<>(); //存储中间结果栈
        //扫描中缀表达式
        for (String item : ls) {
            if (item.matches("\\d+")) {//如果是数,入s2
                s2.add(item);
            } else if (item.equals("(")) {//是(的情况
                s1.push(item);
            } else if (item.equals(")")) {//是)的情况
                //依次弹出s1栈顶的运算符,并压入s2,直到遇到(为止,然后将这对()扔掉
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //将(弹出s1
            } else {//item是运算符的情况
                //当item的优先级小于s1栈顶的运算符
                //将s1栈顶运算符弹出压入到s2,再次拿出item和s1栈顶的运算符比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //s1为空,或栈顶运算符为(,直接将运算符入s1
                s1.push(item);
            }
        }
        //将s1剩余的运算符加入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将中缀表达式转换成list
    //s="1+((2+3)×4)-5"
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0; //指针沿着字符串的顺序
        String str; // 拼接多位数
        char c; //遍历到的字符
        do {
            //如果c是非数字,加到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //如果是数字,需要考虑多位数问题
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48
                        && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接数组
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式,依次将数据和运算符放到ArrayList中
    public static List<String> getListString(String suffixExpression) {
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
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : ls) {
            //正则表达式取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                stack.push(item);
            } else { //匹配到运算符 item现在是运算符
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //计算两数的结果
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("×")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符错误");
                }
                //res入栈
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//比较运算符优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "×":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
        }
        return result;
    }
}