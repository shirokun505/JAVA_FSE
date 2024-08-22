public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        Employee emp1 = new Employee("E001", "Alice", "Developer", 70000);
        Employee emp2 = new Employee("E002", "Bob", "Manager", 85000);
        Employee emp3 = new Employee("E003", "Charlie", "Analyst", 60000);

        ems.addEmployee(emp1);
        ems.addEmployee(emp2);
        ems.addEmployee(emp3);

        System.out.println("Traversing Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearching for Employee with ID E002:");
        Employee foundEmployee = ems.searchEmployee("E002");
        System.out.println(foundEmployee != null ? foundEmployee : "Employee not found.");

        System.out.println("\nDeleting Employee with ID E002:");
        ems.deleteEmployee("E002");

        System.out.println("\nTraversing Employees after deletion:");
        ems.traverseEmployees();
    }
}
