public class Main {
    public static void main(String[] args) {
        // Create the model (Student)
        Student student = new Student("John Doe", "001", "A");

        // Create the view (StudentView)
        StudentView view = new StudentView();

        // Create the controller (StudentController)
        StudentController controller = new StudentController(student, view);

        // Display the initial student details
        controller.updateView();

        // Update the student details through the controller
        controller.setStudentName("Jane Smith");
        controller.setStudentGrade("B");

        // Display the updated student details
        controller.updateView();
    }
}
