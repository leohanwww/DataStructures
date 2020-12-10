package com.atguigu.recursion;

public class EightQueensProblem {
    //共有多少个皇后
    int max = 8;
    //定义数组,保存皇后存放位置,arr={0,4,7,5,2,6,1,3}表示第一行第一列有一个皇后,第arr[1] 第2行第(4+1)=5列有一个皇后
    int[] array = new int[max];
    static int count =0;
    public static void main(String[] args) {
        EightQueensProblem eightQueensProblem = new EightQueensProblem();
        eightQueensProblem.check(0);
        System.out.println("count="+count);
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) { //n为8时,前8个皇后已经放置完成
            print();
            return;
        }
        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            //把当前皇后n,放到该行第一列
            array[n] = i;
            //判断放置第n个皇后到i列时是否冲突
            if (judge(n)){ //不冲突
                //接着放n+1个皇后
                check(n+1);
            }//如果冲突,不执行if中语句,返回上面array[n]=i,此时i已经增加了1,就是列加了1
        }
    }

    /**
     * description 查看当放置第n个皇后,检测该皇后是否和之前已经摆放的皇后冲突
     * @param n 放上去的第n个皇后
     * @return boolean
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) { //循环检测之前的每一个是否与第n个冲突
            if (array[i] == array[n] || // 判断第n个皇后是否和前面的n-1个皇后在同一列
                    // n-i表示行差  array[n]-array[i]表示列差  行差==列差相等表示在同一斜线
                    Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //将皇后摆放的位置打印
    private void print() {
        count++; //计数器,每次打印都计数,一共有多少种摆放法
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
