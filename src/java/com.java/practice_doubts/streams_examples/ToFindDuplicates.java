package streams_examples;

import java.util.*;

public class ToFindDuplicates {
    public static void main(String[] args) {
         List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,98,42,32,15,32);
         HashSet<Integer> set=new HashSet<>();
         List<Integer> res=myList.stream().filter(s->!set.add(s)).distinct().toList();
         System.out.println("dupliacates"+res);
// unique elements
         Set<Integer> set1=new HashSet<>(myList);
         List<Integer> res2=set1.stream().toList();
         System.out.println("alterntive way"+res2);

    }
    
}
