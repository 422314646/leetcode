package tree;

import java.util.Arrays;
import java.util.jar.JarOutputStream;

public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {7,6,8,5,9};
        //heapSort(arr);
        heapSort1(arr);
        System.out.println(Arrays.toString(arr));
    }


    //编写堆排序(从小道大 需要大顶堆)
    public static void heapSort(int arr[]){
        System.out.println("堆排序");

        /*//分布排序
        adjustHeap(arr, 1, arr.length);//4,9,8,5,6
        System.out.println(Arrays.toString(arr));
        adjustHeap(arr,0,arr.length);
        System.out.println(Arrays.toString(arr));//9,6,8,5,4*/
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //System.out.println(Arrays.toString(arr));
        int temp = 0;
        //将无序序列构建成一个堆，根据升序和降序需求选择大顶堆或者小顶堆
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
        //System.out.println(Arrays.toString(arr));
    }
    //编写堆排序(从大道小 需要小顶堆)
    public static void heapSort1(int[] arr){
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjustHeap1(arr, i, arr.length);
        }
        int temp = 0;
        for (int k = arr.length - 1; k > 0; k--){
            temp = arr[k];
            arr[k] = arr[0];
            arr[0] = temp;
            adjustHeap1(arr, 0, k);
        }
    }

    //将一个数组（二叉树），调整一个大顶堆
    public static void adjustHeap(int arr[], int i, int length){
        int temp = arr[i];//取出当前元素，保存在临时变量
        //开始调整数组(二叉树) i * 2 + 1 是当前节点的左子树
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]){//说明右子树大于左子树
                j++;//指向较大的右子树
            }
            if (arr[j] > temp){//找到大于当前的节点
                arr[i] = arr[j];//把大的节点值给当前的节点
                i = j;
            }else {
                break;
            }
            arr[i] = temp;//把当前节点的值给刚才最大的节点
        }
    }

    //将一个数值（二叉树）,调整一个小顶堆
    public static void adjustHeap1(int[] arr, int i, int length){
        int temp = arr[i];

        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
           if (j + 1 < length && arr[j + 1] < arr[j]){
               j++;
           }
           if (temp > arr[j]){
               arr[i] = arr[j];
               i = j;
           } else {
               break;
           }
           arr[i] = temp;
        }
    }
}
