import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class User {
    private String name;
    private String email;
    private String password;
    private String shippingAddress;

    public User(String name, String email, String password, String shippingAddress) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.shippingAddress = shippingAddress;
    }
    
    public String toString() {
        return "User{Name='" + name + "', Email='" + email + "', Shipping Address='" + shippingAddress + "'}";
    }
}

public class UserRegistration {
    private static final List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();

            System.out.println("Enter your email:");
            String email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please try again.");
                continue;
            }

            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            System.out.println("Enter your shipping address:");
            String shippingAddress = scanner.nextLine();

            if (name.isEmpty() || password.isEmpty() || shippingAddress.isEmpty()) {
                System.out.println("All fields are required. Please try again.");
                continue;
            }

            User user = new User(name, email, password, shippingAddress);
            users.add(user);
            System.out.println("Registration successful!");

            System.out.println("Current registered users:");
            for (User registeredUser : users) {
                System.out.println(registeredUser);
            }

            System.out.println("Do you want to register another user? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                break;
            }
        }
        
        scanner.close();
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}