package 정렬;

import java.util.*;

public class 합병정렬 {

    public static int[] solution(int[] arr){
        
        mergeSort(arr, 0, arr.length - 1);

        return arr;
    }

    public static void mergeSort(int[] arr, int left, int right){
        
        if(left < right){
            int mid = (left + right)/2;

            // 분할
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);

            // 정복 - 합치면서 정렬
            int[] sorted = new int[right-left+1];
            int index = 0;
            int leftP = left;
            int rightP = mid+1;
            while(leftP <= mid || rightP <= right){
                if(rightP>right || (left<= mid && arr[leftP] < arr[rightP])){
                    sorted[index] = arr[leftP];
                    leftP++;
                }
                else{
                    sorted[index] = arr[rightP];
                    rightP++;
                }
                index++;
            }

            // // 남아있다면 추가해줌
            // if(leftP < mid){
            //     for(int i=leftP; i<mid+1; i++){
            //         sorted[index] = arr[leftP];
            //         index++;
            //     }
            // }
            // if(rightP < right){
            //     for(int i=rightP; i<=right; i++){
            //         sorted[index] = arr[rightP];
            //         index++;
            //     }
            // }


            // 정렬된것 원본에 반영
            for(int i=0; i<sorted.length; i++){
                arr[i+left] = sorted[i];
            }
            System.out.println(left);
            System.out.println(right);
            System.out.println(Arrays.toString(sorted));
            System.out.println(Arrays.toString(arr));
            System.out.println();
        }
    
    }

    public static void copyArr(int[] arr, int[] temp){
        for(int i=0; i<arr.length; i++){
            temp[i] = arr[i];
        }
    }

    // public static void merge(int left, int right, int mid){

    // }


    public static void main(String[] args){

        int[] arr = {8, 5, 6, 1, 4, 3, 9, 7, 2};

        System.out.println(Arrays.toString(solution(arr)));

    }
}
