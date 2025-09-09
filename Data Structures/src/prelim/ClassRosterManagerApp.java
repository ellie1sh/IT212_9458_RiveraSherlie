package prelim;

import java.util.Scanner;

/**
 * Class Roster Manager Application
 * Demonstrates MyFixedSizeArrayList with Student objects
 * Perfect for limited capacity scenarios - Maximum 5 students per section
 */
public class ClassRosterManagerApp {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CLASS ROSTER MANAGER");
        System.out.println("Using MyFixedSizeArrayList (Fixed Size = 5)\n");
        
        // Create a fixed-size roster for a class section
        MyFixedSizeArrayList<Student> classRoster = new MyFixedSizeArrayList<>();
        
        // Ask user whether to use sample data or input their own
        System.out.print("Do you want to (1) Use sample data or (2) Input your own data? Enter 1 or 2: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Student[] students = new Student[6]; // Array to hold up to 6 students (5 + 1 for overflow test)
        
        if (choice == 2) {
            // User input mode
            System.out.println("\nNote: The roster has a maximum capacity of 5 students.");
            System.out.print("How many students do you want to add? (We'll test overflow if > 5): ");
            int numStudents = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            numStudents = Math.min(numStudents, 6); // Cap at 6 for demonstration
            
            for (int i = 0; i < numStudents; i++) {
                System.out.println("\n--- Enter Student " + (i + 1) + " Details ---");
                
                System.out.print("Student Name: ");
                String name = scanner.nextLine();
                
                System.out.print("ID Number: ");
                String idNumber = scanner.nextLine();
                
                System.out.print("Status (ENROLLED/NOT_ENROLLED): ");
                String status = scanner.nextLine();
                
                System.out.print("Department (e.g., Computer Science, Engineering): ");
                String department = scanner.nextLine();
                
                System.out.print("Course (e.g., Data Structures): ");
                String course = scanner.nextLine();
                
                System.out.print("Year Level (e.g., 1st Year, 2nd Year): ");
                String yearLevel = scanner.nextLine();
                
                System.out.print("GPA (0.0 - 4.0): ");
                double gpa = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                
                students[i] = new Student(name, idNumber, status, department, course, yearLevel, gpa);
            }
        } else {
            // Use sample data
            students[0] = new Student("Alice Johnson", "2021001", "ENROLLED", 
                                         "Computer Science", "Data Structures", "2nd Year", 3.8);
            students[1] = new Student("Bob Smith", "2021002", "ENROLLED", 
                                         "Computer Science", "Data Structures", "2nd Year", 3.5);
            students[2] = new Student("Carol Davis", "2021003", "ENROLLED", 
                                         "Engineering", "Data Structures", "2nd Year", 3.9);
            students[3] = new Student("David Wilson", "2021004", "ENROLLED", 
                                         "Computer Science", "Data Structures", "2nd Year", 3.2);
            students[4] = new Student("Eva Brown", "2021005", "ENROLLED", 
                                         "Engineering", "Data Structures", "2nd Year", 3.7);
            students[5] = new Student("Frank Miller", "2021006", "NOT_ENROLLED", 
                                         "Computer Science", "Data Structures", "2nd Year", 3.4);
        }
        
        try {
            System.out.println("\nENROLLING STUDENTS:");
            System.out.println("======================");
            
            // Add students to the roster (up to 5)
            int studentsToAdd = 0;
            for (Student student : students) {
                if (student != null) studentsToAdd++;
            }
            
            for (int i = 0; i < Math.min(5, studentsToAdd); i++) {
                classRoster.insert(students[i]);
                System.out.println("Enrolled: " + students[i].getStudentName());
            }
            
            // Display current roster
            classRoster.display();
            
            // Try to add one more student if we have 6 (this should throw an exception)
            if (studentsToAdd > 5 && students[5] != null) {
                System.out.println("ATTEMPTING TO EXCEED CAPACITY:");
                System.out.println("===================================");
                classRoster.insert(students[5]);
            }
            
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Cannot enroll more students - section is full!\n");
        }
        
        // Demonstrate search functionality
        System.out.println("SEARCHING FOR STUDENTS:");
        System.out.println("===========================");
        
        System.out.print("\nDo you want to search for a student? (y/n): ");
        String searchChoice = scanner.nextLine();
        
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter student ID to search: ");
            String searchId = scanner.nextLine();
            
            try {
                Student searchStudent = new Student();
                searchStudent.setIdNumber(searchId);
                Student found = classRoster.getElement(searchStudent);
                System.out.println("Found student: " + found.getStudentName());
                System.out.println("Department: " + found.getDepartment());
                System.out.println("GPA: " + found.getGpa());
            } catch (Exception e) {
                System.out.println("Student with ID " + searchId + " not found.");
            }
        } else if (classRoster.getSize() > 1) {
            // Default search demonstration
            try {
                Student foundStudent = classRoster.getElement(students[1]);
                System.out.println("Found: " + foundStudent.getStudentName());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        // Demonstrate deletion
        System.out.println("\nDROPPING A STUDENT:");
        System.out.println("========================");
        
        System.out.print("Do you want to drop a student? (y/n): ");
        String dropChoice = scanner.nextLine();
        
        if (dropChoice.equalsIgnoreCase("y") && classRoster.getSize() > 0) {
            System.out.print("Enter student ID to drop: ");
            String dropId = scanner.nextLine();
            
            Student dropStudent = new Student();
            dropStudent.setIdNumber(dropId);
            
            boolean deleted = classRoster.delete(dropStudent);
            if (deleted) {
                System.out.println("Successfully dropped student with ID: " + dropId);
            } else {
                System.out.println("Student with ID " + dropId + " not found.");
            }
        } else if (classRoster.getSize() > 2 && students[2] != null) {
            // Default deletion demonstration
            boolean deleted = classRoster.delete(students[2]);
            if (deleted) {
                System.out.println("Successfully dropped " + students[2].getStudentName());
            }
        }
        
        classRoster.display();
        
        // Now we can add a waitlisted student if there's space
        if (classRoster.getSize() < classRoster.getCapacity()) {
            System.out.println("ENROLLING FROM WAITLIST:");
            System.out.println("============================");
            
            System.out.print("Do you want to add a student from waitlist? (y/n): ");
            String waitlistChoice = scanner.nextLine();
            
            if (waitlistChoice.equalsIgnoreCase("y")) {
                System.out.println("\n--- Enter Waitlisted Student Details ---");
                
                System.out.print("Student Name: ");
                String name = scanner.nextLine();
                
                System.out.print("ID Number: ");
                String idNumber = scanner.nextLine();
                
                System.out.print("Department: ");
                String department = scanner.nextLine();
                
                System.out.print("Course: ");
                String course = scanner.nextLine();
                
                System.out.print("Year Level: ");
                String yearLevel = scanner.nextLine();
                
                System.out.print("GPA: ");
                double gpa = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                
                Student waitlistStudent = new Student(name, idNumber, "ENROLLED", 
                                                     department, course, yearLevel, gpa);
                
                try {
                    classRoster.insert(waitlistStudent);
                    System.out.println("Successfully enrolled " + name + " from waitlist!");
                    classRoster.display();
                } catch (ListOverflowException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (students[5] != null) {
                // Default waitlist enrollment if using sample data
                try {
                    students[5].setStatus("ENROLLED");
                    classRoster.insert(students[5]);
                    classRoster.display();
                } catch (ListOverflowException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        
        // Show final statistics
        System.out.println("FINAL ROSTER STATISTICS:");
        System.out.println("============================");
        System.out.println("Total enrolled: " + classRoster.getSize());
        System.out.println("Maximum capacity: " + classRoster.getCapacity());
        System.out.println("Available spots: " + (classRoster.getCapacity() - classRoster.getSize()));
        System.out.println("Is full: " + classRoster.isFull());
        System.out.println("Is empty: " + classRoster.isEmpty());
        
        System.out.println("\nCONCLUSION:");
        System.out.println("MyFixedSizeArrayList is perfect for scenarios with strict capacity limits!");
        System.out.println("- Prevents overcrowding in class sections");
        System.out.println("- Memory efficient with fixed allocation");
        System.out.println("- Fast operations for small, controlled datasets");
        
        scanner.close();
    }
}