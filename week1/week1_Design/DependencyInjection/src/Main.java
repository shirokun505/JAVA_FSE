public class Main {
    public static void main(String[] args) {
        // Create a CustomerRepository implementation
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // Inject the repository into the service
        CustomerService customerService = new CustomerService(customerRepository);

        // Use the service to find a customer by ID
        Customer customer = customerService.getCustomerById("001");

        // Display customer details
        System.out.println(customer);
    }
}
