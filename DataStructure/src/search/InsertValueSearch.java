package search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertValueSearch {

    public static void main(String[] args) {
        int[] num = {1,1,2,5,5,5,6,7,8};
        /*for (int i = 0; i < 100; i++){
            num[i] = i + 1;
        }*/
        System.out.println(insertValueSearch(num, 0, num.length - 1, 5));
    }

    public static List<Integer> insertValueSearch(int[] arr, int left, int right, int findVal){
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return new ArrayList<>();
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right]);

        if (findVal < arr[mid]){
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else if (findVal > arr[mid]){
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else {
            List<Integer> res = new ArrayList<>();
            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                res.add(temp);
                temp--;
            }
            res.add(mid);
            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != findVal){
                    break;
                }
                res.add(temp);
                temp++;
            }
            return res;
        }
    }
}
