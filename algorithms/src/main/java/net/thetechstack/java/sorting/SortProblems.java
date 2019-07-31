package net.thetechstack.java.sorting;
import java.util.*;

public class SortProblems{
    public static void groupAnagrams(String[] input){
        Map<String, List<String>> map = new HashMap<>();
        Arrays.stream(input).forEach(string -> {
            String sortedString = sortChars(string);
            map.merge(sortedString, List.of(string), (a, b) -> {
                if(a == null){
                    a = new ArrayList<String>();
                    a.addAll(b);
                    return a;
                }else{
                    a.addAll(b);
                    return a;
                }
            });
        });
    }
    public static String sortChars(String input){
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    public static void main(String[] args){
        SortProblems.groupAnagrams(
            new String[]{"abc","ball","cba","cat","bat","tab"});
    }
}