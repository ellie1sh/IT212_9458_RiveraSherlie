package prelim;

import java.util.Scanner;

/**
 * Class Roster Manager Application
 * Demonstrates MyFixedSizeArrayList with Student objects
 * Perfect for limited capacity scenarios - Maximum 5 students per section
 */
public class ClassRosterManagerApp {
    
    public static void main(String[] args) {
        System.out.println("CLASS ROSTER MANAGER (Interactive)");
        System.out.println("Using MyFixedSizeArrayList (Fixed Size = 5)\n");

        MyFixedSizeArrayList<Student> classRoster = new MyFixedSizeArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1) Enroll student");
            System.out.println("2) Search student by ID");
            System.out.println("3) Drop student by ID");
            System.out.println("4) Display roster");
            System.out.println("5) Exit");
            System.out.print("Enter choice (1-5): ");

            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    try {
                        Student newStudent = promptStudent(scanner);
                        classRoster.insert(newStudent);
                    } catch (ListOverflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.print("Enter ID number to search: ");
                    String searchId = scanner.nextLine().trim();
                    try {
                        Student probe = new Student();
                        probe.setIdNumber(searchId);
                        Student found = classRoster.getElement(probe);
                        System.out.println("Found: " + found);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.print("Enter ID number to drop: ");
                    String dropId = scanner.nextLine().trim();
                    Student toDrop = new Student();
                    toDrop.setIdNumber(dropId);
                    boolean deleted = classRoster.delete(toDrop);
                    if (!deleted) {
                        System.out.println("No matching student to drop.");
                    }
                    break;
                case "4":
                    classRoster.display();
                    System.out.println("FINAL ROSTER STATISTICS:");
                    System.out.println("============================");
                    System.out.println("Total enrolled: " + classRoster.getSize());
                    System.out.println("Maximum capacity: " + classRoster.getCapacity());
                    System.out.println("Available spots: " + (classRoster.getCapacity() - classRoster.getSize()));
                    System.out.println("Is full: " + classRoster.isFull());
                    System.out.println("Is empty: " + classRoster.isEmpty());
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number 1-5.");
            }
            System.out.println();
        }

        System.out.println("Goodbye.");
        scanner.close();
    }

    private static Student promptStudent(Scanner scanner) {
        System.out.println("Enter student details to enroll:");
        System.out.print("Full name: ");
        String name = scanner.nextLine().trim();

        System.out.print("ID number: ");
        String id = scanner.nextLine().trim();

        System.out.print("Department: ");
        String department = scanner.nextLine().trim();

        System.out.print("Course: ");
        String course = scanner.nextLine().trim();

        System.out.print("Year level (e.g., 1st Year): ");
        String yearLevel = scanner.nextLine().trim();

        double gpa = promptDouble(scanner, "GPA (0.0 - 4.0): ");

        return new Student(name, id, "ENROLLED", department, course, yearLevel, gpa);
    }

    private static double promptDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}