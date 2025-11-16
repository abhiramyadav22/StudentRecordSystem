import java.util.ArrayList;
import java.util.Scanner;

// Model class for student
class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Marks=" + marks +
                '}';
    }
}

public class StudentManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from 1 to 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Student Record Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
    }

    // Add
    private static void addStudent() {
        System.out.println("\n--- Add Student ---");
        int id = getIntInput("Enter Student ID: ");
        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        double marks = getDoubleInput("Enter Student Marks: ");

        // Optional: check if ID already exists
        if (findStudentById(id) != null) {
            System.out.println("Student with this ID already exists. Try a different ID.");
            return;
        }

        Student student = new Student(id, name, marks);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    // View
    private static void viewStudents() {
        System.out.println("\n--- View All Students ---");
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Update
    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        int id = getIntInput("Enter Student ID to update: ");

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter new name (leave blank to keep same): ");
        String newName = scanner.nextLine();

        String marksInput;
        System.out.print("Enter new marks (leave blank to keep same): ");
        marksInput = scanner.nextLine();

        if (!newName.trim().isEmpty()) {
            student.setName(newName);
        }

        if (!marksInput.trim().isEmpty()) {
            try {
                double newMarks = Double.parseDouble(marksInput);
                student.setMarks(newMarks);
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks input. Keeping old marks.");
            }
        }

        System.out.println("Student updated successfully.");
    }

    // Delete
    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int id = getIntInput("Enter Student ID to delete: ");

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully.");
    }

    // Helper: find student by ID
    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // Safe int input
    private static int getIntInput(String message) {
        int value;
        while (true) {
            System.out.print(message);
            try {
                value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    // Safe double input
    private static double getDoubleInput(String message) {
        double value;
        while (true) {
            System.out.print(message);
            try {
                value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}
