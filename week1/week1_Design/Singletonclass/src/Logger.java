public class Logger {
    // Step 2.1: Create a private static instance of the Logger class
    private static Logger instance;

    // Step 2.2: Make the constructor private to prevent instantiation
    private Logger() {
        // private constructor to prevent instantiation
    }

    // Step 2.3: Provide a public static method to get the instance of the Logger class
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Example logging method
    public void log(String message) {
        System.out.println("Log message: " + message);
    }
}
