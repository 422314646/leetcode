package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
* 归并排序
* */
public class MergetSort {

    public static void main(String[] args) {
        //int[] arr = {8,4,5,7,1,3,6,2};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*800000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间=" + dateStr);
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1, temp);
        Date date1 = new Date();
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间=" + dateStr1);
        //System.out.println(Arrays.toString(arr));
    }


    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid + 1, right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /*
    *@param arr 排序的原始数组
    *@param left 左边有序序列的初始索引
    *@param mid 中间索引
    *@param right 右边有序序列的初始索引
    *@param temp 作为中转的数组
    * */
    public static void merge(int[] arr, int left,int mid, int right, int[] temp){
        int i = left;   //初始化i，左边的有序的初始化索引
        int j = mid + 1; //初始化j，右边有序的初始化索引
        int t = 0; //指向temp的数组的当前索引

        //（一）
        //先把左右两边（有序）的数据按照规则填充到temp
        //直到左右两边的有序序列，有一遍处理完毕为止
        while(i <= mid && j <= right){
            //左边有序的当前元素小于等于右边有序的当前元素
            //即将左边的当前元素拷贝到temp里面
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //（二）
        //把剩余数据的一边的数据依次全部填充到temp
       while (i <= mid){
           temp[t] = arr[i];
           t++;
           i++;
       }
       while (j <= right){
           temp[t] = arr[j];
           t++;
           j++;
       }
        //（三）
        //将temp数组的元素拷贝到arr
        //注意：不是拷贝所有的元素
        t = 0;
       int tempLeft = left;
       while (tempLeft <= right){
           arr[tempLeft] = temp[t];
           t++;
           tempLeft++;
       }
    }
}
