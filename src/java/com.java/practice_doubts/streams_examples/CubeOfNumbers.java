package streams_examples;

import java.util.*;

public class  CubeOfNumbers{
    public static void main(String[] args) {
       List<Integer> integerList = Arrays.asList(4,5,6,7,1,2,3);
       integerList.stream()
                  .map(i -> i*i*i)
                  .filter(i -> i>50)
                  .forEach(System.out::println);
    }
}
