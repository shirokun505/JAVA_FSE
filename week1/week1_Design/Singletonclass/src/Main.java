public class Main {
    public static void main(String[] args) {
        // Retrieve the singleton instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log messages using both instances
        logger1.log("Logging from the first instance.");
        logger2.log("Logging from the second instance.");

        // Check if both logger instances are the same
        if (logger1 == logger2) {
            System.out.println("Both logger instances are the same.");
        } else {
            System.out.println("Logger instances are different.");
        }
    }
}
