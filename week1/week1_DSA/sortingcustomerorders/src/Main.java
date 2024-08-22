public class Main {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "John Doe", 250.75),
            new Order("O002", "Jane Smith", 100.50),
            new Order("O003", "Alice Johnson", 350.00),
            new Order("O004", "Robert Brown", 150.25),
            new Order("O005", "Emily Davis", 200.00)
        };

        // Bubble Sort
        System.out.println("Orders sorted by totalPrice using Bubble Sort:");
        BubbleSort.bubbleSort(orders);
        for (Order order : orders) {
            System.out.println(order);
        }

        // Reset order array for Quick Sort
        orders = new Order[]{
            new Order("O001", "John Doe", 250.75),
            new Order("O002", "Jane Smith", 100.50),
            new Order("O003", "Alice Johnson", 350.00),
            new Order("O004", "Robert Brown", 150.25),
            new Order("O005", "Emily Davis", 200.00)
        };

        // Quick Sort
        System.out.println("\nOrders sorted by totalPrice using Quick Sort:");
        QuickSort.quickSort(orders, 0, orders.length - 1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
