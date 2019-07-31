package net.thetechstack.java.sorting;
import java.util.*;

public class QuickSort{
    public void quickSort(int[] input, int start, int end, int level){
        if(start >= end) return;
        int pivot = partition(input, start, end);
        System.out.printf("%s sort -> %s pivot -> %d %n", space(level), subArray(input, start, end), input[pivot]);
        quickSort(input, start, pivot-1, level++);
        quickSort(input, pivot+1, end, level++);
    }
    public int partition(int[] input, int start, int end){
        int pivot = input[end];
        int pIndex = start;
        for(int i=start; i<end; i++){
            if(input[i] < pivot){
                swap(input, i, pIndex);
                pIndex++;
            }
        }
        swap(input, end, pIndex);
        return pIndex;
    }
    public String space(int count){
        StringBuilder out = new StringBuilder();
        for(int i=0; i<count; i++){
            out.append("  ");
        }
        return out.toString();
    }
    public String subArray(int[] input, int start, int end){
        StringBuilder out = new StringBuilder();
        for(int i=start; i<=end; i++){
            out.append(input[i] + ", ");
        }
        return out.toString();
    }
    public void swap(int[] input, int a, int b){
        int temp = input[b];
        input[b] = input[a];
        input[a] = temp;
    }
    public static void main(String args[]){
        QuickSort sort = new QuickSort();
        sort.quickSort(new int[]{2,5,1,6,7,3}, 0, 5, 0);
    }
}