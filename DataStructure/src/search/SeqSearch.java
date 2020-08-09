package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,1,2,5,6,7,8,8,9,-1};
        System.out.println(seqSearch(arr, 1));
    }

    public static List<Integer> seqSearch(int[] arr, int value){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++){
            if (value == arr[i]){
                res.add(i);
            }
        }
        return res;
    }
}
