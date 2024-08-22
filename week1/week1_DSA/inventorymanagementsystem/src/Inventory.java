import java.util.HashMap;

public class Inventory {
    private HashMap<String, Product> pro;

    public Inventory() {
        pro = new HashMap<>();
    }

    public void addProduct(Product product) {
        pro.put(product.getProductId(), product);
    }

    public void updateProduct(Product product) {
        if (pro.containsKey(product.getProductId())) {
            pro.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        pro.remove(productId);
    }

    public Product getProduct(String productId) {
        return pro.get(productId);
    }
}
