package streams_examples;

import java.util.Arrays;
import java.util.List;

public class FindTotalNumberOfElements {
    public static void main(String[] args) {
          List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,98,42,32,15,32);
          long count=myList.stream().count();
          System.out.println(count);
    }
    
}
