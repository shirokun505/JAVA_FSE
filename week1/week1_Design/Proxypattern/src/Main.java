public class Main {
    public static void main(String[] args) {
        // Create a ProxyImage object
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Load and display the first image
        image1.display(); // Loading from disk, then displaying

        // Display the first image again (should not load from disk)
        image1.display(); // Directly displaying from cache

        // Load and display the second image
        image2.display(); // Loading from disk, then displaying
    }
}
