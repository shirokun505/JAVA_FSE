public class Main {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        Task task1 = new Task("T001", "Design Database", "In Progress");
        Task task2 = new Task("T002", "Develop API", "Not Started");
        Task task3 = new Task("T003", "Test Application", "In Progress");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        System.out.println("Traversing Tasks:");
        taskList.traverseTasks();

        System.out.println("\nSearching for Task with ID T002:");
        Task foundTask = taskList.searchTask("T002");
        System.out.println(foundTask != null ? foundTask : "Task not found.");

        System.out.println("\nDeleting Task with ID T002:");
        taskList.deleteTask("T002");

        System.out.println("\nTraversing Tasks after deletion:");
        taskList.traverseTasks();
    }
}
