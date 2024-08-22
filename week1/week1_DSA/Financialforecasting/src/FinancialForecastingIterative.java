public class FinancialForecastingIterative {

    // Iterative method to calculate future value
    public static double calculateFutureValueIterative(double presentValue, double growthRate, int periods) {
        double futureValue = presentValue;
        for (int i = 0; i < periods; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double presentValue = 1000.0;
        double growthRate = 0.05;  // 5% growth rate
        int periods = 10;  // 10 years into the future

        double futureValue = calculateFutureValueIterative(presentValue, growthRate, periods);
        System.out.println("The future value after " + periods + " years is: " + futureValue);
    }
}
