package streams_examples;

public class duplicatesusingStreams {
        public static void main(String[] args) {
            List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
    
            myList.stream()
                    .filter(i -> Collections.frequency(myList, i) > 1)
                    .distinct()
                    .forEach(System.out::println);
    }
    
}
