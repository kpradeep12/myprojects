package net.thetechstack.java.sorting;
import java.util.*;
import java.util.stream.*;

public class MergeSort{
    public void mergeSort(int[] input, int low, int high){
        if(low < high){
            int mid = ((high - low) / 2) + low;
            mergeSort(input, low, mid);
            mergeSort(input, mid+1, high);
            merge(input, low, mid, high);
            System.out.println(Arrays.toString(input));
        }
    }
    public void merge(int[] input, int low, int mid, int high){
        Queue<Integer> fir = new LinkedList<>();
        Queue<Integer> sec = new LinkedList<>();
        IntStream.range(low, mid+1).forEach(i -> fir.offer(input[i]));
        IntStream.range(mid+1, high+1).forEach(i -> sec.offer(input[i]));
        int i = low;
        while(!(fir.isEmpty() || sec.isEmpty())){
            if(fir.peek() < sec.peek()){
                input[i++] = fir.poll();
            }else{
                input[i++] = sec.poll();
            }
        }
        while(!fir.isEmpty()) input[i++] = fir.poll();
        while(!sec.isEmpty()) input[i++] = sec.poll();
    }
    public static void main(String args[]){
        MergeSort sort = new MergeSort();
        sort.mergeSort(new int[]{2,6,1,4,9,3}, 0, 5);
    }
}