import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        String email = "revathi@gmail.com";
        String nullEmail = null;
        
        // Creating Optional
        Optional<String> opt1 = Optional.of(email); // throws NPE if null
        Optional<String> opt2 = Optional.ofNullable(nullEmail); // safe for null
        Optional<String> opt3 = Optional.empty();
        
        // isPresent + get
        if (opt1.isPresent()) {
            System.out.println("Email: " + opt1.get().toUpperCase());
        }
        
        // orElse - default value
        String result = opt2.orElse("no-email@default.com");
        System.out.println("Result: " + result);
        
        // orElseGet - supplier
        String result2 = opt3.orElseGet(() -> "generated@default.com");
        System.out.println("Generated: " + result2);
        
        // ifPresent - do action if value exists
        opt1.ifPresent(e -> System.out.println("Sending mail to: " + e));
        
        // map + filter chain
        String domain = Optional.ofNullable(email)
                .filter(e -> e.contains("@"))
                .map(e -> e.substring(e.indexOf("@") + 1))
                .orElse("unknown");
        System.out.println("Domain: " + domain);
    }
}