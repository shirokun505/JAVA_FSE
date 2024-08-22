public class FinancialForecastingFuture {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        // Base case: if no more periods, return the present value
        if (periods == 0) {
            return presentValue;
        }
        // Recursive case: calculate future value for the remaining periods
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        double presentValue = 1000.0;
        double growthRate = 0.05;  // 5% growth rate
        int periods = 10;  // 10 years into the future

        double futureValue = calculateFutureValue(presentValue, growthRate, periods);
        System.out.println("The future value after " + periods + " years is: " + futureValue);
    }
}
