package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        //int[] arr = {53,3,542,0,748,14,214};
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*8000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间=" + dateStr);
        radSort(arr);
        Date date1 = new Date();
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间=" + dateStr1);
        //System.out.println(Arrays.toString(arr));
    }
    public static void radSort(int[] arr){

        //根据前面推导过程得到最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = (max + "").length();


        //第一轮(针对每一个元素的个位进行排序处理)
        //dingy一个二维数组，表示10个桶，每一个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每一个一维数组（桶），大小定位arr.length
        //3.明确，基数排序使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每一个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //比如：bucketElementCounts[0],记录的就是bucket[0]桶放入数据个数
        int[] bucketElementCounts = new int[10];

        //使用循环处理代码
        for (int i = 0, n = 1; i < maxLength; i++, n = n * 10){
            //第一次是个位，第二次是十位，第三次是百位，，，
            for (int j = 0; j < arr.length; j++){
                //取出每一个元素的个数
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index = 0;
            //遍历每一个桶，并将桶中是数据，放入原数组
            for(int k = 0; k < bucketElementCounts.length; k++){
                if (bucketElementCounts[k] != 0){
                    //循环改桶即第k个桶（即第k个一维数组），放入
                    for (int l = 0; l <bucketElementCounts[k]; l++){
                        //取出元素放入arr
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //一定要将bucketElementCounts[k]置零不然会出错！！！！！！
                bucketElementCounts[k] = 0;
            }
            //System.out.println("第"+(i+1)+"轮排序过后，arr = " + Arrays.toString(arr));
        }


        /*//第一轮（针对每一个元素的个位进行排序处理）
        for (int j = 0; j < arr.length; j++){
            //取出每一个元素的个数
            int digitOfElement = arr[j] % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        int index = 0;
        //遍历每一个桶，并将桶中是数据，放入原数组
        for(int k = 0; k < bucketElementCounts.length; k++){
            if (bucketElementCounts[k] != 0){
                //循环改桶即第k个桶（即第k个一维数组），放入
                for (int l = 0; l <bucketElementCounts[k]; l++){
                    //取出元素放入arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //一定要将bucketElementCounts[k]置零不然会出错！！！！！！
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮排序过后，arr = " + Arrays.toString(arr));

        //第二轮（针对每一个元素的个位进行排序处理）
        for (int j = 0; j < arr.length; j++){
            //取出每一个元素的个数
            int digitOfElement = arr[j] / 10 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        index = 0;
        //遍历每一个桶，并将桶中是数据，放入原数组
        for(int k = 0; k < bucketElementCounts.length; k++){
            if (bucketElementCounts[k] != 0){
                //循环改桶即第k个桶（即第k个一维数组），放入
                for (int l = 0; l <bucketElementCounts[k]; l++){
                    //取出元素放入arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //一定要将bucketElementCounts[k]置零不然会出错！！！！！！
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮排序过后，arr = " + Arrays.toString(arr));


        //第三轮（针对每一个元素的个位进行排序处理）
        for (int j = 0; j < arr.length; j++){
            //取出每一个元素的个数
            int digitOfElement = arr[j] / 100 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        index = 0;
        //遍历每一个桶，并将桶中是数据，放入原数组
        for(int k = 0; k < bucketElementCounts.length; k++){
            if (bucketElementCounts[k] != 0){
                //循环改桶即第k个桶（即第k个一维数组），放入
                for (int l = 0; l <bucketElementCounts[k]; l++){
                    //取出元素放入arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //一定要将bucketElementCounts[k]置零不然会出错！！！！！！
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮排序过后，arr = " + Arrays.toString(arr));*/

    }
}
