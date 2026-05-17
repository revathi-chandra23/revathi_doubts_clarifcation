package streams_examples;

public class UniqueElemnts {
    
    public static void main(String[] args) {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);

        myList.stream()
                .filter(i -> Collections.frequency(myList, i) == 1)
                .forEach(System.out::println);
    }
}
