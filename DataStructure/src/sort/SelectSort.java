package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {


        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*800000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间=" + dateStr);
        //int[] arr = {101,34,119,1,8,66,-1,5,-55,-2};
        selectSort(arr);
        Date date1 = new Date();
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间=" + dateStr1);
        //System.out.println(Arrays.toString(arr));
        //System.out.println("=======================");
        //int[] arr1= {101,34,119,1,8,66,-1,5,-55,-2};
        //selectSort1(arr1);
        //System.out.println(Arrays.toString(arr1));
    }

    //选择排序(从小到大)
    public static void selectSort(int[] arr){

        for (int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++){
                if (min > arr[j]){
                    minIndex = j;
                    min = arr[j];
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }


       /* //使用逐步推导的方式来
        //原始数组101，34，119，1
        //第一趟1，34，119，101
        int minIndex = 0;
        int min = arr[0];

        for (int i = 1; i < arr.length; i++){
            if (min > arr[i]){
                minIndex = i;
                min = arr[i];
            }
        }
        if (minIndex != 0){
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一趟排序：");
        System.out.println(Arrays.toString(arr));

        minIndex = 1;
        min = arr[1];
        for (int i = (1 + 1); i < arr.length; i++){
            if (min > arr[i]){
                minIndex = i;
                min = arr[i];
            }
        }
        if (minIndex != 1){
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二趟排序：");
        System.out.println(Arrays.toString(arr));

        minIndex = 2;
        min = arr[2];
        for (int i = (1 + 2); i < arr.length; i++){
            if (min > arr[i]){
                minIndex = i;
                min = arr[i];
            }
        }
        if (minIndex != 2){
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三趟排序：");
        System.out.println(Arrays.toString(arr));*/
    }

    //选择排序(从大到小)
    public static void selectSort1(int[] arr){

        for (int j = 0; j < arr.length - 1; j++){
            int maxIndex = j;
            int max = arr[j];
            for(int i = 1 + j; i < arr.length; i++){
                if (max < arr[i]){
                    max = arr[i];
                    maxIndex = i;
                }
            }
            if (maxIndex != j){
                arr[maxIndex] = arr[j];
                arr[j] = max;
            }
        }

        /*//第一趟排序
        int maxIndex = 0;
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if (max < arr[i]){
                max = arr[i];
                maxIndex = i;
            }
        }
        if (maxIndex != 0){
            arr[maxIndex] = arr[0];
            arr[0] = max;
        }
        System.out.println("第一趟排序(从大到小)：");
        System.out.println(Arrays.toString(arr));

        maxIndex = 1;
        max = arr[1];
        for(int i = 2; i < arr.length; i++){
            if (max < arr[i]){
                max = arr[i];
                maxIndex = i;
            }
        }
        if (maxIndex != 1){
            arr[maxIndex] = arr[1];
            arr[1] = max;
        }
        System.out.println("第二趟排序(从大到小)：");
        System.out.println(Arrays.toString(arr));

        maxIndex = 2;
        max = arr[2];
        for(int i = 3; i < arr.length; i++){
            if (max < arr[i]){
                max = arr[i];
                maxIndex = i;
            }
        }
        if (maxIndex != 2){
            arr[maxIndex] = arr[2];
            arr[2] = max;
        }
        System.out.println("第三趟排序(从大到小)：");
        System.out.println(Arrays.toString(arr));*/
    }



}
