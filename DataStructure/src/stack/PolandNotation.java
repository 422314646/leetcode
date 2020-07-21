package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //说明
        //1.1+（（2+3）*4）-5=>123+4*+5-
        //先将1+（（2+3）*4）的中缀表达式存入相应的list中
        //3.将得到的中缀表达式对应的list=》后缀表达式
        String str = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(str);
        System.out.println("中缀表达式：" + list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("后缀表达式：" + list1);

        //先定义逆波兰式
        //（3 + 4） * 5 - 6 => 3 4 + 5 * 6 -
        //说明为了方便，逆波兰式表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1.先将“3 4 + 5 * 6 -” 放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);
        int res = calculate(list1);
        System.out.println("该运算式结果=" + res);
    }

    //3.将得到的中缀表达式对应的list=》后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //说明因为s2栈没有出栈的操作后面还需要逆序打印比较麻烦
        //所以采用list进行存储
        //Stack<String> s2 = new Stack<String>();//存储中间结果的栈
        List<String> s2 = new ArrayList<String>();//存储中间结果的list

        for (String s: ls) {
            if (s.matches("\\d+")){//3)遇到操作数时，将其压s2;
                s2.add(s);
            }else if (s.equals("(")){//(1)如果是左括号“(”，则直接压入s1
                s1.push(s);
            }else if (s.equals(")")){
                while (!s1.peek().equals("(")){
                    //(2)如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号 丢弃
                    s2.add(s1.pop());
                }
                s1.pop();//消除"("
            }else {
                //当s的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次与s1中的栈顶运算符相比较
                //我们缺少一个优先级b比较优先级高低的方法
                while (s1.size() > 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s)){
                    s2.add(s1.pop());
                }
                s1.push(s);//将s压入栈
            }
        }
        //s1将加入s2中
        while (s1.size() > 0){
            s2.add(s1.pop());
        }
        return s2;//因为存入list中，因此顺序输出就是后缀表达式
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

    //构建一个把表达式转成list存放的中缀表达式
    public static List<String> toInfixExpressionList(String Expression){
        List<String> list = new ArrayList<String>();
        int i = 0;
        String str;//对多位数的拼接
        char c;//每遍历一个字符存入c
        do {
            if ((c = Expression.charAt(i)) < 48 || (c = Expression.charAt(i)) > 57){
                list.add("" + c);
                i++;
            }else {
                str = "";
                while (i < Expression.length() && ((c = Expression.charAt(i)) >= 48 && (c = Expression.charAt(i)) <= 57)){
                    str = str + c;
                    i++;
                }
                list.add(str);
            }
        }while (i < Expression.length());
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

//编写一个类，比较返回运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法返回相应优先级数字
    public static int getValue(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("没有该运算符");
                break;
        }
        return res;
    }
}