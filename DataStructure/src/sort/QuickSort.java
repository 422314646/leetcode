package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*80000000);
        }*/
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间=" + dateStr);
        int[] arr = {-7,9,10,7,8,11,12};
        quickSort(arr,0,arr.length - 1);
        Date date1 = new Date();
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间=" + dateStr1);
        //System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left; //左下标
        int r = right; //右下标
        int pivot = arr[(left + right) / 2]; //中间值
        int temp = 0;
        //while循环为了让比pivot小的值在左边，大的在右边
        while (l < r){
            //在pivot左边一直找，找到一个大于或者等于pivot值，才退出
            while ( arr[l] < pivot){
                l++;
            }
            //在pivot右边一直找，找到一个小于或者等于pivot值，才退出
            while ( arr[r] > pivot){//进行优化的while少一次不必要的循环
                r--;
            }
            //如果r = l 说明pivot左边的值已经全部小于pivot 右边的值已经全部大于pivot
            if (l >= r){//进行优化的
                break;
            }
            //交换
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            //如果交换完后，发现arr[l] == pivot值相等于r--，前移
            if(arr[l] == pivot){
                r--;
            }
            //如果交换完后，发现arr[r] == pivot值相等于l++，后移
            if (arr[r] == pivot){
                l++;
            }
        }

        if (r == l){
            r--;
            l++;
        }
        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }
}
