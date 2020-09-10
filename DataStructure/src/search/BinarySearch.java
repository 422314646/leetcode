package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,5,5,5,6,7,8,9,10};
        System.out.println(binarySearch(arr,0,arr.length - 1,5));
        System.out.println(binarySearch2(arr,0,arr.length - 1, 5));
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){
        if (left > right){
            return -1;
        }
        int mid = (right + left) / 2;
        //int mid = left + (right - left) / 2
        if (findVal < arr[mid]){
            return binarySearch(arr, left, mid - 1, findVal);
        }else if (findVal > arr[mid]){
            return binarySearch(arr, mid + 1, right, findVal);
        }else {
            return mid;
        }
    }

    public static int binarySearch1(int[] arr, int target){
        int left = 0;
        int right = arr.length;
        int mid = (left + right) / 2;

        while (left <= right){
            if (arr[mid] == target){
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        if (left > right){
            return new ArrayList<>();
        }
        int mid = (right + left) / 2;
        if (findVal < arr[mid]){
            return binarySearch2(arr, left, mid - 1, findVal);
        }else if (findVal > arr[mid]){
            return binarySearch2(arr, mid + 1, right, findVal);
        }else {
            List<Integer> resList = new ArrayList<>();

            int temp = mid - 1;
            while (true){
                if (temp < 0 || findVal != arr[temp]){
                    break;
                }
                resList.add(temp);
                temp--;
            }
            resList.add(mid);
            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != findVal){
                    break;
                }
                resList.add(temp);
                temp++;
            }
            return resList;
        }
    }
}
