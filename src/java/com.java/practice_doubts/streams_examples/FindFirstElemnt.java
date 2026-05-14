package streams_examples;

import java.util.Arrays;
import java.util.List;

public class FindFirstElemnt {
    public static void main(String[] args) {
          List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,98,42,32,15,32);
          int res=myList.stream().findFirst().orElse(-1);
            System.out.println(res);
    }
    
}
