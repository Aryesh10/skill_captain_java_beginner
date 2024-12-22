import java.util.ArrayList;
import java.util.List;

class Product {
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    public Product(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Price: " + price + ", Quantity: " + quantity;
    }
}

class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addToCart(Product product) {
        products.add(product);
        System.out.println(product.getProductName() + " has been added to the cart.");
    }

    public void removeFromCart(Product product) {
        if (products.remove(product)) {
            System.out.println(product.getProductName() + " has been removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public void updateQuantity(Product product, int quantity) {
        for (Product p : products) {
            if (p.getProductId() == product.getProductId()) {
                p.setQuantity(quantity);
                System.out.println("Updated " + p.getProductName() + " quantity to " + quantity + ".");
                return;
            }
        }
        System.out.println("Product not found in the cart.");
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Products in the cart:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void checkout() {
        if (products.isEmpty()) {
            System.out.println("The cart is empty. Add products before checkout.");
        } else {
            double total = 0;
            System.out.println("Proceeding to checkout:");
            for (Product product : products) {
                total += product.getPrice() * product.getQuantity();
                System.out.println(product);
            }
            System.out.println("Total Amount: $" + total);
            System.out.println("Checkout complete. Thank you for shopping!");
            products.clear();
        }
    }
}

public class addToCart {
    public static void main(String[] args) {
        Product product1 = new Product(1, "Laptop", 999.99, 1);
        Product product2 = new Product(2, "Headphones", 199.99, 2);
        Product product3 = new Product(3, "Mouse", 49.99, 1);

        Cart cart = new Cart();

        cart.addToCart(product1);
        cart.addToCart(product2);

        cart.viewCart();

        cart.updateQuantity(product2, 3);

        cart.removeFromCart(product3);

        cart.addToCart(product3);

        cart.viewCart();

        cart.checkout();
    }
}
