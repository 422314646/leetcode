package recursion;

public class Queue8 {
    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有：" + queue8.flag);
        System.out.println("一共循环：" + queue8.count);
    }

    int max = 8;//定义一共有多少个皇后
    int[] array = new int[max];

    int flag = 0, count = 0;

    //编写放第几个皇后
    public void check(int n){
        if (n == max){//是从0开始放置的，当放低8个（实际第9个）所以就是正确路径
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++){
            array[n] = i;
            if (jude(n)){//不冲突就放置下一个皇后
                check( n + 1);
            }
            //冲突就执行array[n] = i++
        }
    }

    //判断是否冲突
    public Boolean jude(int n){
        count++;
        for (int i = 0; i < n; i++){
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //打印每一种情况
    public void print(){
        flag++;
        for (int i = 0; i < max; i++){
            System.out.print((array[i] + 1) + " ");
        }
        System.out.println();
    }
}
