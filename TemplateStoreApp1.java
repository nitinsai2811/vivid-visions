import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TemplateStoreApp {

    static class Product {
        int id;
        String name;
        double price;
        String downloadLink;

        public Product(int id, String name, double price, String downloadLink) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.downloadLink = downloadLink;
        }
    }

    public static void main(String[] args) {
        // Create products
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Business Logo", 20.0, "https://example.com/logo1.zip"));
        products.add(new Product(2, "Modern Template", 25.0, "https://example.com/template1.zip"));
        products.add(new Product(3, "Creative Logo", 30.0, "https://example.com/logo2.zip"));
        products.add(new Product(4, "Corporate Template", 35.0, "https://example.com/template2.zip"));

        // Cart to store added products
        List<Product> cart = new ArrayList<>();

        // Main frame
        JFrame frame = new JFrame("Template Store");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Product list panel
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(products.size(), 1));
        for (Product product : products) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel nameLabel = new JLabel(product.name + " - $" + product.price);
            JButton addButton = new JButton("Add to Cart");
            addButton.addActionListener(e -> {
                cart.add(product);
                JOptionPane.showMessageDialog(frame, product.name + " added to cart.");
            });
            panel.add(nameLabel);
            panel.add(addButton);
            productPanel.add(panel);
        }

        // Scrollable products
        JScrollPane scrollPane = new JScrollPane(productPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Cart and checkout button
        JPanel bottomPanel = new JPanel();
        JButton viewCartButton = new JButton("View Cart");
        JButton checkoutButton = new JButton("Proceed to Payment");

        viewCartButton.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty.");
            } else {
                StringBuilder cartContent = new StringBuilder("Cart Items:\n");
                for (Product product : cart) {
                    cartContent.append(product.name).append(" - $").append(product.price).append("\n");
                }
                JOptionPane.showMessageDialog(frame, cartContent.toString());
            }
        });

        checkoutButton.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty.");
            } else {
                StringBuilder paymentContent = new StringBuilder("Payment successful! Download links:\n");
                for (Product product : cart) {
                    paymentContent.append(product.name).append(": ").append(product.downloadLink).append("\n");
                }
                JOptionPane.showMessageDialog(frame, paymentContent.toString());
                cart.clear(); // Clear the cart after payment
            }
        });

        bottomPanel.add(viewCartButton);
        bottomPanel.add(checkoutButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Show frame
        frame.setVisible(true);
    }
}
