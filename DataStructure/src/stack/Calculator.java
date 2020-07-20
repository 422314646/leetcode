package stack;

public class Calculator {
    public static void main(String[] args) {
        //根据思路完成相应功能
        String expression  = "70+20*6-4";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;
        int num = 0;
        int num1 = 0;
        int oper = 0;
        int res = 0;
        char ch =' ';//将每次扫描到的char保存到这里
        String keepNum = "";//用于拼接多位数的
        while (true){
            //依次得到expression的每一个字符
            ch = expression.charAt(index);
            //进行判断ch是什么，做相应判断
            if (operStack.isOper(ch)){//如果是运算符
                if (!operStack.isEmpty()){
                    //不为空，做相应的处理
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num = numStack.pop();
                        num1 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num, num1, oper);
                        //把运算符的结果入数栈
                        numStack.push(res);
                        //把当前的符号栈入符号栈
                        operStack.push(ch);
                    }else {
                        //如果大于直接入符号栈
                        operStack.push(ch);
                    }
                }else {//如果为空直接入栈
                    operStack.push(ch);
                }

            }else {
                //如果是数直接入数栈
                //numStack.push(ch - 48);
                //1.当处理多位数数时，不能发现是一个数就立即入栈
                //2.在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3.因此我们需要定义一个变量字符串，用于拼接
                //处理多位数
                keepNum = keepNum + ch;
                //如果ch已经是最后一位，就直接入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.charAt(index+1))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

            }
            //让index + 1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()){
                break;
            }

        }
        while (true){
            //如果符号为空，则计算到最后的结果，数栈只有一个数字
            if (operStack.isEmpty()){
                break;
            }
            num = numStack.pop();
            num1 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num, num1, oper);
            //把运算符的结果入数栈
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}

//定义一个栈但是需要扩展相应的功能
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈 push
    public void push(int value){
        if (isFull()){
            System.out.println("栈满，不能入栈");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 pop
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，不能出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级，优先级是我们定的
    //数字越大，则优先级就越高
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '-' || oper == '+'){
            return 0;
        }else {
            return -1;//目前只有加减乘除
        }
    }
    //增加可以查看栈顶值的方法，而不返回值
    public int peek(){
        return stack[top];
    }

    //判断是不是运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num, int num1, int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num + num1;
                break;
            case '-':
                res = num1 - num;
                break;
            case '*':
                res = num * num1;
                break;
            case '/':
                res = num1 / num;
                break;
            default:
                break;
        }
        return res;
    }
}