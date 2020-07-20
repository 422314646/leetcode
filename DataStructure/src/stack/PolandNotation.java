package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰式
        //（3 + 4） * 5 - 6 => 3 4 + 5 * 6 -
        //说明为了方便，逆波兰式表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1.先将“3 4 + 5 * 6 -” 放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);
        int res = calculate(rpnList);
        System.out.println("该运算式结果=" + res);
    }

    //将逆波兰式依次将数据和运算符放入到ArrayList
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        List<String> list = new ArrayList<String>();
        String[] split = suffixExpression.split(" ");
        for (String s: split) {
            list.add(s);
        }
        return list;
    }

    //完成对逆波兰式的运算
    /*
    * 1)从左至右扫描，将3和4压入堆栈;
    * 2)遇到+运算符，因此弹出4和3 (4为栈项元素，3为次顶元素)，计算出3+4的值，得7，再将7入
    * 3) 将5入栈;
    * 4)接下来是X运算符，因此弹出5和7，计算出7X5=35，将35入栈;
    * 5) 将6入栈;
    * 6) 最后是-运算符，计算出35-6的值， 即29， 由此得出最终结果
    * */

    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String s: ls) {
            //所用正则表达式取出数字
            if (s.matches("\\d+")){
                //入栈
                stack.push(s);
            }else {
                int res = 0;
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                if (s.equals("+")){
                    res = num1 + num2;
                }else if (s.equals("-")){
                    res = num2 - num1;
                }else if (s.equals("/")){
                    res = num2 / num1;
                }else if (s.equals("*")){
                    res = num1 * num2;
                }else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
