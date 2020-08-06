/*
* 88. 合并两个有序数组
* */
public class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m, t = 0;
        while (t < n){
            nums1[i] = nums2[t];
            i++;
            t++;
        }

        int[] temp = new int[m + n];
        mergeSort(nums1, 0, (m + n) - 1, temp);
    }

    //分+合方法
    public void mergeSort(int[] arr, int left, int right, int[] temp){
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

    //归并排序
    public void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            } else {
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
}
