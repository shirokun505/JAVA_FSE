public class Main {
    public static void main(String[] args) {
        // Create a StockMarket object
        StockMarket stockMarket = new StockMarket();

        // Create observers
        Observer mobileApp = new MobileApp("Mobile App");
        Observer webApp = new WebApp("Web App");

        // Register observers
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        // Change the stock price and notify observers
        stockMarket.setStockPrice(100.50);
        stockMarket.setStockPrice(101.00);

        // Deregister one observer and change the price again
        stockMarket.deregisterObserver(webApp);
        stockMarket.setStockPrice(102.75);
    }
}
