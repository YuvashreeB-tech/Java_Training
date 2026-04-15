import java.util.*;

// 🔹 Product Class (Encapsulation)
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void display() {
        System.out.println(id + " - " + name + " - ₹" + price);
    }
}

// 🔹 Cart Class (Uses HashMap)
class Cart {
    private HashMap<Product, Integer> items = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void showCart() {
        System.out.println("\n🛒 Cart Items:");
        double total = 0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double cost = p.getPrice() * qty;

            System.out.println(p.getName() + " x " + qty + " = ₹" + cost);
            total += cost;
        }

        System.out.println("Total: ₹" + total);
    }

    public void checkout() {
        System.out.println("\n✅ Order Placed Successfully!");
        items.clear();
    }
}

// 🔹 User Class (Inheritance Example)
class User {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    public void showUser() {
        System.out.println("User: " + name);
    }
}

// 🔹 Customer Class (Inheritance)
class Customer extends User {
    private Cart cart;

    public Customer(String name) {
        super(name);
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }
}

// 🔹 Main Class
public class ECommerceSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Product List (ArrayList)
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(1, "Laptop", 50000));
        products.add(new Product(2, "Phone", 20000));
        products.add(new Product(3, "Headphones", 2000));

        Customer customer = new Customer("Yuva");

        while (true) {
            System.out.println("\n1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n📦 Products:");
                    for (Product p : products) {
                        p.display();
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    for (Product p : products) {
                        if (p.getId() == id) {
                            customer.getCart().addToCart(p, qty);
                            System.out.println("✔ Added to cart!");
                        }
                    }
                    break;

                case 3:
                    customer.getCart().showCart();
                    break;

                case 4:
                    customer.getCart().showCart();
                    customer.getCart().checkout();
                    break;

                case 5:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}