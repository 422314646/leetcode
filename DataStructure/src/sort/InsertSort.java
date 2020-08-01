package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {

        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*80);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间=" + dateStr);
        //int[] arr = {101, 34, 119, 1, 89, -78, 50,999,-1,-8};
        insertSort(arr);
        Date date1 = new Date();
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间=" + dateStr1);

    }

    //插入排序
    public static void insertSort(int[] arr){

        int insertVal = 0, insertIndex = 0;
       for(int i = 1; i < arr.length; i++){
           insertVal = arr[i];
           insertIndex = i - 1;

           while (insertIndex >= 0 && insertVal < arr[insertIndex]){
               arr[insertIndex + 1] = arr[insertIndex];
               insertIndex--;
           }

           if (insertIndex + 1 != i){
               arr[insertIndex + 1] = insertVal;
               System.out.println("第"+ i +"趟插入");
               System.out.println(Arrays.toString(arr));
           }

       }

    }

        /*//第一趟排序
        int insertVal = arr[1];
        int insertIndex = 1 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第一趟插入");
        System.out.println(Arrays.toString(arr));

        //第二次排序
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第二趟插入");
        System.out.println(Arrays.toString(arr));

        //第三次
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal <= arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第三趟插入");
        System.out.println(Arrays.toString(arr));
    }*/

}
