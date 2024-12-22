import java.util.*;

class Product {
    private String productId;
    private String productName;
    private String description;
    private String manufacturer;
    private int warrantyPeriod;
    private Date registrationDate;

    public Product(String productId, String productName, String description, String manufacturer, int warrantyPeriod) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.warrantyPeriod = warrantyPeriod;
        this.registrationDate = new Date();
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public boolean isWarrantyActive() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(registrationDate);
        cal.add(Calendar.MONTH, warrantyPeriod);
        return new Date().before(cal.getTime());
    }

    @Override
    public String toString() {
        return "Product ID: " + productId +
                "\nProduct Name: " + productName +
                "\nDescription: " + description +
                "\nManufacturer: " + manufacturer +
                "\nWarranty Period: " + warrantyPeriod + " months" +
                "\nWarranty Status: " + (isWarrantyActive() ? "Active" : "Expired");
    }
}

// ProductRegistrationSystem class
public class ProductRegistrationSystem {
    private Map<String, Product> productRegistry;

    public ProductRegistrationSystem() {
        productRegistry = new HashMap<>();
    }

    public void registerProduct(Scanner scanner) {
        System.out.println("Enter Product ID:");
        String productId = scanner.nextLine();
        if (productRegistry.containsKey(productId)) {
            System.out.println("Product ID already exists. Please use a unique ID.");
            return;
        }

        System.out.println("Enter Product Name:");
        String productName = scanner.nextLine();

        System.out.println("Enter Description:");
        String description = scanner.nextLine();

        System.out.println("Enter Manufacturer:");
        String manufacturer = scanner.nextLine();

        System.out.println("Enter Warranty Period (in months):");
        int warrantyPeriod;
        try {
            warrantyPeriod = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid warranty period. Please enter a number.");
            return;
        }

        Product product = new Product(productId, productName, description, manufacturer, warrantyPeriod);
        productRegistry.put(productId, product);
        System.out.println("Product registered successfully.");
    }

    public void displayAllProducts() {
        if (productRegistry.isEmpty()) {
            System.out.println("No products registered.");
            return;
        }

        for (Product product : productRegistry.values()) {
            System.out.println(product);
            System.out.println("-----------------------------");
        }
    }

    public void searchProductById(Scanner scanner) {
        System.out.println("Enter Product ID to search:");
        String productId = scanner.nextLine();
        Product product = productRegistry.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println(product);
        }
    }

    public void generateReport() {
        int activeCount = 0;
        int expiredCount = 0;

        for (Product product : productRegistry.values()) {
            if (product.isWarrantyActive()) {
                activeCount++;
            } else {
                expiredCount++;
            }
        }

        System.out.println("Total Products Registered: " + productRegistry.size());
        System.out.println("Products with Active Warranty: " + activeCount);
        System.out.println("Products with Expired Warranty: " + expiredCount);
    }

    public static void main(String[] args) {
        ProductRegistrationSystem system = new ProductRegistrationSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nProduct Registration System");
            System.out.println("1. Register Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    system.registerProduct(scanner);
                    break;
                case 2:
                    system.displayAllProducts();
                    break;
                case 3:
                    system.searchProductById(scanner);
                    break;
                case 4:
                    system.generateReport();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}