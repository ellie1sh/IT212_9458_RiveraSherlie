package prelim;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Class Roster Manager Application
 * Demonstrates MyFixedSizeArrayList with Student objects
 * Perfect for limited capacity scenarios - Maximum 5 students per section
 * Now with comprehensive input validation
 */
public class ClassRosterManagerApp {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CLASS ROSTER MANAGER");
        System.out.println("Using MyFixedSizeArrayList (Fixed Size = 5)\n");
        
        // Create a fixed-size roster for a class section
        MyFixedSizeArrayList<Student> classRoster = new MyFixedSizeArrayList<>();
        
        // Ask user whether to use sample data or input their own with validation
        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.print("Do you want to (1) Use sample data or (2) Input your own data? Enter 1 or 2: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice != 1 && choice != 2) {
                    System.out.println("❌ Invalid choice! Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number (1 or 2).");
                scanner.nextLine(); // Clear invalid input
            }
        }
        
        Student[] students = new Student[6]; // Array to hold up to 6 students (5 + 1 for overflow test)
        
        if (choice == 2) {
            // User input mode with validation
            System.out.println("\nNote: The roster has a maximum capacity of 5 students.");
            int numStudents = 0;
            while (numStudents <= 0) {
                System.out.print("How many students do you want to add? (1-6, we'll test overflow if > 5): ");
                try {
                    numStudents = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (numStudents <= 0) {
                        System.out.println("❌ Please enter a positive number!");
                    } else if (numStudents > 6) {
                        System.out.println("❌ Maximum 6 students for demonstration. Please enter 1-6.");
                        numStudents = 0;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("❌ Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
            
            numStudents = Math.min(numStudents, 6); // Cap at 6 for demonstration
            
            for (int i = 0; i < numStudents; i++) {
                System.out.println("\n--- Enter Student " + (i + 1) + " Details ---");
                
                // Validate student name
                String name = "";
                while (name.trim().isEmpty()) {
                    System.out.print("Student Name: ");
                    name = scanner.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("❌ Student name cannot be empty!");
                    } else if (!name.matches("[a-zA-Z\\s]+")) {
                        System.out.println("❌ Student name should only contain letters and spaces!");
                        name = "";
                    }
                }
                
                // Validate ID number
                String idNumber = "";
                boolean idExists = false;
                while (idNumber.trim().isEmpty() || idExists) {
                    System.out.print("ID Number (e.g., 2024001): ");
                    idNumber = scanner.nextLine();
                    idExists = false;
                    
                    if (idNumber.trim().isEmpty()) {
                        System.out.println("❌ ID number cannot be empty!");
                    } else if (!idNumber.matches("[0-9]{7}")) {
                        System.out.println("❌ ID number should be exactly 7 digits!");
                        idNumber = "";
                    } else {
                        // Check for duplicate ID
                        for (int j = 0; j < i; j++) {
                            if (students[j] != null && students[j].getIdNumber().equals(idNumber)) {
                                System.out.println("❌ This ID number already exists! Please enter a unique ID.");
                                idExists = true;
                                break;
                            }
                        }
                    }
                }
                
                // Validate status
                String status = "";
                while (!status.equalsIgnoreCase("ENROLLED") && !status.equalsIgnoreCase("NOT_ENROLLED")) {
                    System.out.print("Status (ENROLLED/NOT_ENROLLED): ");
                    status = scanner.nextLine().toUpperCase();
                    if (!status.equals("ENROLLED") && !status.equals("NOT_ENROLLED")) {
                        System.out.println("❌ Please enter either ENROLLED or NOT_ENROLLED!");
                    }
                }
                
                // Validate department
                String department = "";
                while (department.trim().isEmpty()) {
                    System.out.print("Department (e.g., Computer Science, Engineering): ");
                    department = scanner.nextLine();
                    if (department.trim().isEmpty()) {
                        System.out.println("❌ Department cannot be empty!");
                    } else if (!department.matches("[a-zA-Z\\s]+")) {
                        System.out.println("❌ Department should only contain letters and spaces!");
                        department = "";
                    }
                }
                
                // Validate course
                String course = "";
                while (course.trim().isEmpty()) {
                    System.out.print("Course (e.g., Data Structures): ");
                    course = scanner.nextLine();
                    if (course.trim().isEmpty()) {
                        System.out.println("❌ Course cannot be empty!");
                    }
                }
                
                // Validate year level
                String yearLevel = "";
                while (!yearLevel.matches("(1st|2nd|3rd|4th|5th)\\s+Year")) {
                    System.out.print("Year Level (1st Year, 2nd Year, 3rd Year, 4th Year, or 5th Year): ");
                    yearLevel = scanner.nextLine();
                    if (!yearLevel.matches("(1st|2nd|3rd|4th|5th)\\s+Year")) {
                        System.out.println("❌ Please enter a valid year level (e.g., 1st Year, 2nd Year)!");
                    }
                }
                
                // Validate GPA
                double gpa = -1;
                while (gpa < 0.0 || gpa > 4.0) {
                    System.out.print("GPA (0.0 - 4.0): ");
                    try {
                        gpa = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        if (gpa < 0.0 || gpa > 4.0) {
                            System.out.println("❌ GPA must be between 0.0 and 4.0!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Invalid input! Please enter a number between 0.0 and 4.0.");
                        scanner.nextLine(); // Clear invalid input
                        gpa = -1;
                    }
                }
                
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
        
        String searchChoice = "";
        while (!searchChoice.equalsIgnoreCase("y") && !searchChoice.equalsIgnoreCase("n")) {
            System.out.print("\nDo you want to search for a student? (y/n): ");
            searchChoice = scanner.nextLine().trim();
            if (!searchChoice.equalsIgnoreCase("y") && !searchChoice.equalsIgnoreCase("n")) {
                System.out.println("❌ Please enter 'y' for yes or 'n' for no.");
            }
        }
        
        if (searchChoice.equalsIgnoreCase("y")) {
            String searchId = "";
            while (searchId.trim().isEmpty()) {
                System.out.print("Enter student ID to search (7 digits): ");
                searchId = scanner.nextLine();
                if (searchId.trim().isEmpty()) {
                    System.out.println("❌ ID cannot be empty!");
                } else if (!searchId.matches("[0-9]{7}")) {
                    System.out.println("❌ ID should be exactly 7 digits!");
                    searchId = "";
                }
            }
            
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
        
        String dropChoice = "";
        while (!dropChoice.equalsIgnoreCase("y") && !dropChoice.equalsIgnoreCase("n")) {
            System.out.print("Do you want to drop a student? (y/n): ");
            dropChoice = scanner.nextLine().trim();
            if (!dropChoice.equalsIgnoreCase("y") && !dropChoice.equalsIgnoreCase("n")) {
                System.out.println("❌ Please enter 'y' for yes or 'n' for no.");
            }
        }
        
        if (dropChoice.equalsIgnoreCase("y") && classRoster.getSize() > 0) {
            String dropId = "";
            while (dropId.trim().isEmpty()) {
                System.out.print("Enter student ID to drop (7 digits): ");
                dropId = scanner.nextLine();
                if (dropId.trim().isEmpty()) {
                    System.out.println("❌ ID cannot be empty!");
                } else if (!dropId.matches("[0-9]{7}")) {
                    System.out.println("❌ ID should be exactly 7 digits!");
                    dropId = "";
                }
            }
            
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
            
            String waitlistChoice = "";
            while (!waitlistChoice.equalsIgnoreCase("y") && !waitlistChoice.equalsIgnoreCase("n")) {
                System.out.print("Do you want to add a student from waitlist? (y/n): ");
                waitlistChoice = scanner.nextLine().trim();
                if (!waitlistChoice.equalsIgnoreCase("y") && !waitlistChoice.equalsIgnoreCase("n")) {
                    System.out.println("❌ Please enter 'y' for yes or 'n' for no.");
                }
            }
            
            if (waitlistChoice.equalsIgnoreCase("y")) {
                System.out.println("\n--- Enter Waitlisted Student Details ---");
                
                // Validate student name
                String name = "";
                while (name.trim().isEmpty()) {
                    System.out.print("Student Name: ");
                    name = scanner.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("❌ Student name cannot be empty!");
                    } else if (!name.matches("[a-zA-Z\\s]+")) {
                        System.out.println("❌ Student name should only contain letters and spaces!");
                        name = "";
                    }
                }
                
                // Validate ID number
                String idNumber = "";
                boolean idExists = false;
                while (idNumber.trim().isEmpty() || idExists) {
                    System.out.print("ID Number (7 digits): ");
                    idNumber = scanner.nextLine();
                    idExists = false;
                    
                    if (idNumber.trim().isEmpty()) {
                        System.out.println("❌ ID number cannot be empty!");
                    } else if (!idNumber.matches("[0-9]{7}")) {
                        System.out.println("❌ ID number should be exactly 7 digits!");
                        idNumber = "";
                    } else {
                        // Check for duplicate ID in current roster
                        for (int j = 0; j < classRoster.getSize(); j++) {
                            Student existingStudent = classRoster.get(j);
                            if (existingStudent != null && existingStudent.getIdNumber().equals(idNumber)) {
                                System.out.println("❌ This ID number already exists in the roster!");
                                idExists = true;
                                break;
                            }
                        }
                    }
                }
                
                // Validate department
                String department = "";
                while (department.trim().isEmpty()) {
                    System.out.print("Department: ");
                    department = scanner.nextLine();
                    if (department.trim().isEmpty()) {
                        System.out.println("❌ Department cannot be empty!");
                    } else if (!department.matches("[a-zA-Z\\s]+")) {
                        System.out.println("❌ Department should only contain letters and spaces!");
                        department = "";
                    }
                }
                
                // Validate course
                String course = "";
                while (course.trim().isEmpty()) {
                    System.out.print("Course: ");
                    course = scanner.nextLine();
                    if (course.trim().isEmpty()) {
                        System.out.println("❌ Course cannot be empty!");
                    }
                }
                
                // Validate year level
                String yearLevel = "";
                while (!yearLevel.matches("(1st|2nd|3rd|4th|5th)\\s+Year")) {
                    System.out.print("Year Level (1st Year, 2nd Year, etc.): ");
                    yearLevel = scanner.nextLine();
                    if (!yearLevel.matches("(1st|2nd|3rd|4th|5th)\\s+Year")) {
                        System.out.println("❌ Please enter a valid year level!");
                    }
                }
                
                // Validate GPA
                double gpa = -1;
                while (gpa < 0.0 || gpa > 4.0) {
                    System.out.print("GPA (0.0 - 4.0): ");
                    try {
                        gpa = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        if (gpa < 0.0 || gpa > 4.0) {
                            System.out.println("❌ GPA must be between 0.0 and 4.0!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Invalid input! Please enter a number between 0.0 and 4.0.");
                        scanner.nextLine(); // Clear invalid input
                        gpa = -1;
                    }
                }
                
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