package Sort;

import java.util.Arrays;

public class SortPractice {
    public static void main(String[] args){
        int[] arr ={9,8,7,6,5,4,3,2,1,0};
//        insertSort(arr);
        String str = " [[0, 1, 5], [0, 3, 2], [0, 4, 3], [1, 4, 1], [3, 4, 10], [1, 2, 2], [2, 5, 3], [4, 5, 4]]";
        str = str.replace('[','{');
        str = str.replace(']','}');
        System.out.println(str);
    }
    public static void insertSort(int[] arr){
        System.out.println("INSERTION SORT with "+ Arrays.toString(arr));
        int key = arr[0];
        int j ;
        for(int i =1 ;i <arr.length -1; i++){
            key = arr[i];
            for(j = i-1;  j>=0 && key<arr[j];j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = key;
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("sorted => "+Arrays.toString(arr));
    }
}
