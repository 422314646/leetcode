package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*800000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间=" + dateStr);
        //int[] arr = {8,9,1,7,2,3,5,4,6,0};
        //shellSort(arr);
        shellSort1(arr);
        Date date1 = new Date();
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间=" + dateStr1);
        //System.out.println(Arrays.toString(arr));
    }

    //使用逐步推导的方式来编写希尔排序(交换法)
    public static void shellSort(int[] arr){
        int temp = 0, count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++){
                //遍历所以的元素（共5组，每一组2个数），长度为5
                for (int j = i - gap; j >= 0; j -= gap){
                    //如果当前元素大于加上长度后的那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第"+(++count)+"次排序：" + Arrays.toString(arr));
        }

        /*//希尔排序的第一轮排序
        //因为是希尔排序，所以我们把上面10/2个数分成5组
        for(int i = 5; i < arr.length; i++){
            //遍历所以的元素（共5组，每一组2个数），长度为5
            for (int j = i - 5; j >= 0; j -= 5){
                //如果当前元素大于加上长度后的那个元素，说明需要交换
                if (arr[j] > arr[j + 5]){
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一次希尔排序：" + Arrays.toString(arr));

        //希尔排序第二轮排序
        //上一次为5个组这次为5/2= 2个组
        for (int i = 2; i < arr.length; i++){
            //遍历所有的元素（共2个组，每一组5个数），长度为2
            for (int j = i - 2; j >= 0; j -= 2){
                //如果当前元素大于加上长度后的那个元素，说明需要交换
                if (arr[j] > arr[j + 2]){
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二次希尔排序：" + Arrays.toString(arr));

        //希尔排序第三轮排序
        //上一次为2个组这次为2/2= 1个组
        for (int i = 1; i < arr.length; i++){
            //遍历所有的元素（共1个组，每一组10个数），长度为1
            for (int j = i - 1; j >= 0; j -= 1){
                //如果当前元素大于加上长度后的那个元素，说明需要交换
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第二次希尔排序：" + Arrays.toString(arr));*/
    }

    //希尔排序（移位法）
    public static void shellSort1(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            for (int i = gap; i < arr.length; i++){
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}
