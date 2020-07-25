package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*800000);
        }
        //int[] arr = {1,2,4,3,5,7};
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间=" + dateStr);
        bubbleSort(arr);

        Date date1 = new Date();
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间=" + dateStr1);
        /*ystem.out.println("排序的顺序");
        System.out.println(Arrays.toString(arr));*/
    }

    //冒泡排序，排序趟数比排序数组的长度少一次，每一趟的次数比上一次减一，第一趟次数为排序数组的长度减一
    public static void bubbleSort(int[] arr){
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            Boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            /*System.out.println("第" + (i + 1) + "趟排序");
            System.out.println(Arrays.toString(arr));*/
            if (!flag){
                break;
            }
        }
    }
}
        /*//第一趟排序
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++){
            if (arr[i] > arr[i + 1]){
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第一趟排序的顺序");
        System.out.println(Arrays.toString(arr));

        //第二趟排序
        for (int i = 0; i < arr.length - 1 - 1; i++){
            if (arr[i] > arr[i + 1]){
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二趟排序的顺序");
        System.out.println(Arrays.toString(arr));

        //第三趟排序
        for (int i = 0; i < arr.length - 1 - 1 - 1; i++){
            if (arr[i] > arr[i + 1]){
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第三趟排序的顺序");
        System.out.println(Arrays.toString(arr));
    }*/
