package streams_examples;

import java.util.Arrays;
import java.util.List;

public class NumberWithStartswithOne {
    public static void main(String[] args) {
        {
             List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);
             List<Integer> res=myList.stream().filter(s->s.toString().startsWith("1")).toList();
             System.out.println(res);
        }
    }
}
