package sort;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] arr = {89,100,78,999,45,7};
        //bubbleSort(arr);
        //selectSort(arr);
        //insertSort(arr);
        //shellSort(arr);
        //quickSort(arr,0,arr.length - 1);
        //int[] temp = new int[arr.length];
        //merget(arr,0,arr.length - 1,temp);
        RadixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = 0; j < arr.length - 1 - i; j++){
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }

    public static void selectSort(int[] arr){

        int min = 0, minIndex = 0;
        for (int i = 0; i < arr.length; i++){
            min = arr[i];
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (i != minIndex){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }

    public static void shellSort(int[] arr){
        int insertVal = 0, insertIndex = 0;
        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2){
            for (int j = gap; j < arr.length; j++){
               insertVal = arr[j];
               insertIndex = j - gap;
               while (insertIndex >= 0 && arr[insertIndex] >insertVal){
                   arr[insertIndex + gap] = arr[insertIndex];
                   insertIndex -= gap;
               }
               arr[insertIndex + gap] = insertVal;
            }
        }
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int mid = arr[(l + r) / 2];
        int temp = 0;
        while (l < r){
            while (arr[l] < mid){
                l++;
            }
            while (arr[r] > mid){
                r--;
            }
            if (r == l){
                break;
            }
             temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[r] == mid){
                l++;
            }
            if (arr[l] == mid){
                r--;
            }
        }

        if (r == l){
            r--;
            l++;
        }
        if (left < r){
            quickSort(arr,left,r);
        }
        if (right > l){
            quickSort(arr,l,right);
        }
    }

    public static void mergetSort(int[] arr, int left, int mid, int right, int[] temp){
        //合
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right){
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

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }

    public static void merget(int[] arr, int left, int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            merget(arr,left,mid,temp);
            merget(arr,mid+1,right,temp);
            mergetSort(arr,left,mid,right,temp);
        }
    }

    public static void RadixSort(int[] arr){

        int[][] buck = new int[10][arr.length];
        int[] count = new int[10];
        int flag = 0;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        for (int num = 1, p = 0; p < maxLength; p++,num = num * 10){
            for (int i = 0; i < arr.length; i++){
                flag = arr[i] / num  % 10;
                buck[flag][count[flag]] = arr[i];
                count[flag]++;
            }

            int index = 0;
            for (int i = 0; i < count.length; i++){
                if (count[i] != 0){
                    for (int k = 0; k < count[i]; k++){
                        arr[index] = buck[i][k];
                        index++;
                    }
                }
                count[i] = 0;
            }
        }
    }
}
