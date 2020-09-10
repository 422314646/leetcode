package dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c){
        //如果只有一个盘
        if (num == 1){
            System.out.println("第一个盘从" + a + "->" +c);
        } else {
            //如果我们有n => 2情况，我们总是可以看做时两个盘1.最下面的一个盘 2.上面的所有盘
            //先把最上面的所有盘a->b 移动中借助c
            hanoiTower(num - 1, a, c, b);
            //把最下面的盘a->c
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //把b塔从b->c,移动借助a
            hanoiTower(num - 1,b , a, c);
        }
    }
}
