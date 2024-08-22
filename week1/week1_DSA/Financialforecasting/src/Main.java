public class Main {
    public static void main(String[] args) {
        // Define initial parameters
        double presentValue = 1000.0;
        double growthRate = 0.05;  // 5% growth rate
        int periods = 10;  // 10 years into the future

        // Calculate future value using the recursive method
        double futureValueRecursive = FinancialForecastingFuture.calculateFutureValue(presentValue, growthRate, periods);
        System.out.println("The future value after " + periods + " years (recursive approach) is: " + futureValueRecursive);

        // Calculate future value using the iterative method
        double futureValueIterative = FinancialForecastingIterative.calculateFutureValueIterative(presentValue, growthRate, periods);
        System.out.println("The future value after " + periods + " years (iterative approach) is: " + futureValueIterative);
    }
}
