package prelim;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Academic Records System Application
 * Demonstrates MyGrowingArrayList with StudentGrade objects
 * Perfect for expanding collections - Grows as more grades are added
 * Now with comprehensive input validation
 */
public class AcademicRecordsSystemApp {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ACADEMIC RECORDS SYSTEM");
        System.out.println("Using MyGrowingArrayList (Dynamic Growth)\n");
        
        // Create a growing list for academic records
        MyGrowingArrayList<StudentGrade> academicRecords = new MyGrowingArrayList<>();
        
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
        
        StudentGrade[] grades;
        
        if (choice == 2) {
            // User input mode with validation
            int numRecords = 0;
            while (numRecords <= 0) {
                System.out.print("How many grade records do you want to enter? (1-50): ");
                try {
                    numRecords = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (numRecords <= 0) {
                        System.out.println("❌ Please enter a positive number!");
                    } else if (numRecords > 50) {
                        System.out.println("❌ That's too many records! Please enter a number between 1 and 50.");
                        numRecords = 0;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("❌ Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
            
            grades = new StudentGrade[numRecords];
            
            for (int i = 0; i < numRecords; i++) {
                System.out.println("\n--- Enter Grade Record " + (i + 1) + " ---");
                
                // Validate student name
                String studentName = "";
                while (studentName.trim().isEmpty()) {
                    System.out.print("Student Name: ");
                    studentName = scanner.nextLine();
                    if (studentName.trim().isEmpty()) {
                        System.out.println("❌ Student name cannot be empty!");
                    } else if (!studentName.matches("[a-zA-Z\\s]+")) {
                        System.out.println("❌ Student name should only contain letters and spaces!");
                        studentName = "";
                    }
                }
                
                // Validate subject code
                String subjectCode = "";
                while (subjectCode.trim().isEmpty()) {
                    System.out.print("Subject Code (e.g., ICS211, MATH101): ");
                    subjectCode = scanner.nextLine().toUpperCase();
                    if (subjectCode.trim().isEmpty()) {
                        System.out.println("❌ Subject code cannot be empty!");
                    } else if (!subjectCode.matches("[A-Z]{2,4}[0-9]{3}")) {
                        System.out.println("❌ Invalid format! Subject code should be like ICS211 or MATH101");
                        subjectCode = "";
                    }
                }
                
                // Validate semester
                String semester = "";
                while (semester.trim().isEmpty()) {
                    System.out.print("Semester (e.g., 1st Semester 2024): ");
                    semester = scanner.nextLine();
                    if (semester.trim().isEmpty()) {
                        System.out.println("❌ Semester cannot be empty!");
                    } else if (!semester.matches(".*[0-9]{4}.*")) {
                        System.out.println("❌ Semester should include a year (e.g., 2024)!");
                        semester = "";
                    }
                }
                
                // Validate midterm grade
                double midtermGrade = -1;
                while (midtermGrade < 0 || midtermGrade > 100) {
                    System.out.print("Midterm Grade (0-100): ");
                    try {
                        midtermGrade = scanner.nextDouble();
                        if (midtermGrade < 0 || midtermGrade > 100) {
                            System.out.println("❌ Grade must be between 0 and 100!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Invalid input! Please enter a number between 0 and 100.");
                        scanner.nextLine(); // Clear invalid input
                        midtermGrade = -1;
                    }
                }
                
                // Validate final grade
                double finalGrade = -1;
                while (finalGrade < 0 || finalGrade > 100) {
                    System.out.print("Final Grade (0-100): ");
                    try {
                        finalGrade = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        if (finalGrade < 0 || finalGrade > 100) {
                            System.out.println("❌ Grade must be between 0 and 100!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Invalid input! Please enter a number between 0 and 100.");
                        scanner.nextLine(); // Clear invalid input
                        finalGrade = -1;
                    }
                }
                
                // Calculate letter grade and status based on average
                double average = (midtermGrade + finalGrade) / 2;
                String letterGrade;
                String status;
                
                if (average >= 95) {
                    letterGrade = "A+";
                    status = "PASSED";
                } else if (average >= 90) {
                    letterGrade = "A";
                    status = "PASSED";
                } else if (average >= 85) {
                    letterGrade = "A-";
                    status = "PASSED";
                } else if (average >= 80) {
                    letterGrade = "B+";
                    status = "PASSED";
                } else if (average >= 75) {
                    letterGrade = "B";
                    status = "PASSED";
                } else if (average >= 70) {
                    letterGrade = "C";
                    status = "PASSED";
                } else if (average >= 60) {
                    letterGrade = "D";
                    status = "PASSED";
                } else {
                    letterGrade = "F";
                    status = "FAILED";
                }
                
                grades[i] = new StudentGrade(studentName, subjectCode, semester, 
                                            midtermGrade, finalGrade, letterGrade, status);
            }
        } else {
            // Use sample data
            grades = new StudentGrade[]{
                new StudentGrade("Alice Johnson", "ICS211", "1st Semester 2024", 
                               88.5, 92.0, "A-", "PASSED"),
                new StudentGrade("Alice Johnson", "MATH101", "1st Semester 2024", 
                               85.0, 87.5, "B+", "PASSED"),
                new StudentGrade("Bob Smith", "ICS211", "1st Semester 2024", 
                               78.0, 82.5, "B", "PASSED"),
                new StudentGrade("Carol Davis", "ICS211", "1st Semester 2024", 
                               95.0, 98.0, "A+", "PASSED"),
                new StudentGrade("David Wilson", "MATH101", "1st Semester 2024", 
                               65.0, 70.0, "C", "PASSED"),
                new StudentGrade("Eva Brown", "ICS211", "1st Semester 2024", 
                               90.0, 93.5, "A", "PASSED"),
                new StudentGrade("Alice Johnson", "ICS311", "2nd Semester 2024", 
                               92.0, 89.0, "A-", "PASSED"),
                new StudentGrade("Bob Smith", "MATH201", "2nd Semester 2024", 
                               80.0, 85.0, "B+", "PASSED"),
                new StudentGrade("Carol Davis", "ICS311", "2nd Semester 2024", 
                               97.0, 96.5, "A+", "PASSED"),
                new StudentGrade("Frank Miller", "ICS211", "2nd Semester 2024", 
                               55.0, 60.0, "D", "FAILED")
            };
        }
        
        try {
            System.out.println("ADDING GRADE RECORDS:");
            System.out.println("=========================");
            
            // Add all grade records - watch the array grow automatically
            for (int i = 0; i < grades.length; i++) {
                academicRecords.insert(grades[i]);
                
                // Show capacity growth at certain points
                if (i == 4 || i == 9) {
                    System.out.println("Current capacity: " + academicRecords.getCapacity());
                }
            }
            
            // Display all records
            academicRecords.display();
            
            System.out.println("SEARCHING FOR SPECIFIC RECORDS:");
            System.out.println("===================================");
            
            // Ask user if they want to search for a specific record with validation
            String searchChoice = "";
            while (!searchChoice.equalsIgnoreCase("y") && !searchChoice.equalsIgnoreCase("n")) {
                System.out.print("\nDo you want to search for a specific record? (y/n): ");
                searchChoice = scanner.nextLine().trim();
                if (!searchChoice.equalsIgnoreCase("y") && !searchChoice.equalsIgnoreCase("n")) {
                    System.out.println("❌ Please enter 'y' for yes or 'n' for no.");
                }
            }
            
            if (searchChoice.equalsIgnoreCase("y")) {
                // Validate search inputs
                String searchName = "";
                while (searchName.trim().isEmpty()) {
                    System.out.print("Enter student name to search: ");
                    searchName = scanner.nextLine();
                    if (searchName.trim().isEmpty()) {
                        System.out.println("❌ Student name cannot be empty!");
                    }
                }
                
                String searchSubject = "";
                while (searchSubject.trim().isEmpty()) {
                    System.out.print("Enter subject code to search: ");
                    searchSubject = scanner.nextLine().toUpperCase();
                    if (searchSubject.trim().isEmpty()) {
                        System.out.println("❌ Subject code cannot be empty!");
                    }
                }
                
                String searchSemester = "";
                while (searchSemester.trim().isEmpty()) {
                    System.out.print("Enter semester to search: ");
                    searchSemester = scanner.nextLine();
                    if (searchSemester.trim().isEmpty()) {
                        System.out.println("❌ Semester cannot be empty!");
                    }
                }
                
                StudentGrade searchGrade = new StudentGrade();
                searchGrade.setStudentName(searchName);
                searchGrade.setSubjectCode(searchSubject);
                searchGrade.setSemester(searchSemester);
                
                try {
                    StudentGrade found = academicRecords.getElement(searchGrade);
                    System.out.println("Found record: " + found.getStudentName() + 
                                     " - " + found.getSubjectCode() + 
                                     " (Grade: " + found.getLetterGrade() + ")");
                } catch (Exception e) {
                    System.out.println("❌ Record not found: " + e.getMessage());
                }
            } else {
                // Default search with sample data
                StudentGrade searchGrade = new StudentGrade();
                searchGrade.setStudentName("Alice Johnson");
                searchGrade.setSubjectCode("ICS211");
                searchGrade.setSemester("1st Semester 2024");
                
                try {
                    StudentGrade found = academicRecords.getElement(searchGrade);
                    System.out.println("Found record: " + found.getStudentName() + 
                                     " - " + found.getSubjectCode() + 
                                     " (Grade: " + found.getLetterGrade() + ")");
                } catch (Exception e) {
                    System.out.println("❌ " + e.getMessage());
                }
            }
            
            System.out.println("\nANALYZING ACADEMIC PERFORMANCE:");
            System.out.println("===================================");
            
            // Ask user which students to analyze with validation
            String analyzeChoice = "";
            while (!analyzeChoice.equalsIgnoreCase("y") && !analyzeChoice.equalsIgnoreCase("n")) {
                System.out.print("\nDo you want to analyze specific students? (y/n): ");
                analyzeChoice = scanner.nextLine().trim();
                if (!analyzeChoice.equalsIgnoreCase("y") && !analyzeChoice.equalsIgnoreCase("n")) {
                    System.out.println("❌ Please enter 'y' for yes or 'n' for no.");
                }
            }
            
            if (analyzeChoice.equalsIgnoreCase("y")) {
                int numStudents = 0;
                while (numStudents <= 0) {
                    System.out.print("How many students do you want to analyze? (1-20): ");
                    try {
                        numStudents = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (numStudents <= 0) {
                            System.out.println("❌ Please enter a positive number!");
                        } else if (numStudents > 20) {
                            System.out.println("❌ That's too many! Please enter a number between 1 and 20.");
                            numStudents = 0;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Invalid input! Please enter a valid number.");
                        scanner.nextLine(); // Clear invalid input
                    }
                }
                
                for (int i = 0; i < numStudents; i++) {
                    String studentName = "";
                    while (studentName.trim().isEmpty()) {
                        System.out.print("Enter student name " + (i + 1) + ": ");
                        studentName = scanner.nextLine();
                        if (studentName.trim().isEmpty()) {
                            System.out.println("❌ Student name cannot be empty!");
                        }
                    }
                    analyzeStudentPerformance(academicRecords, studentName);
                }
            } else {
                // Analyze some default students if they exist
                analyzeStudentPerformance(academicRecords, "Alice Johnson");
                analyzeStudentPerformance(academicRecords, "Bob Smith");
                analyzeStudentPerformance(academicRecords, "Carol Davis");
            }
            
            System.out.println("\nREMOVING A FAILED RECORD:");
            System.out.println("===============================");
            
            // Remove the failed record
            StudentGrade failedRecord = null;
            for (int i = 0; i < academicRecords.getSize(); i++) {
                StudentGrade record = academicRecords.get(i);
                if ("FAILED".equals(record.getStatus())) {
                    failedRecord = record;
                    break;
                }
            }
            
            if (failedRecord != null) {
                boolean deleted = academicRecords.delete(failedRecord);
                if (deleted) {
                    System.out.println("Removed failed record for " + 
                                     failedRecord.getStudentName() + " in " + 
                                     failedRecord.getSubjectCode());
                }
            }
            
            academicRecords.display();
            
            System.out.println("MEMORY OPTIMIZATION:");
            System.out.println("========================");
            System.out.println("Before optimization - Capacity: " + academicRecords.getCapacity());
            academicRecords.trimToSize();
            System.out.println("After optimization - Capacity: " + academicRecords.getCapacity());
            
        } catch (ListOverflowException e) {
            System.out.println("❌ " + e.getMessage());
        }
        
        // Show final statistics
        System.out.println("\nFINAL SYSTEM STATISTICS:");
        System.out.println("============================");
        System.out.println("Total grade records: " + academicRecords.getSize());
        System.out.println("Current capacity: " + academicRecords.getCapacity());
        System.out.println("Memory efficiency: " + 
                         String.format("%.1f%%", (double)academicRecords.getSize()/academicRecords.getCapacity() * 100));
        System.out.println("Is empty: " + academicRecords.isEmpty());
        
        System.out.println("\nCONCLUSION:");
        System.out.println("MyGrowingArrayList is perfect for dynamic, expanding datasets!");
        System.out.println("- Automatically grows as needed");
        System.out.println("- Efficient memory management");
        System.out.println("- Handles unpredictable data growth");
        System.out.println("- Suitable for long-term data collection");
        
        scanner.close();
    }
    
    /**
     * Analyzes academic performance for a specific student
     */
    private static void analyzeStudentPerformance(MyGrowingArrayList<StudentGrade> records, String studentName) {
        System.out.println("\nPerformance Analysis for " + studentName + ":");
        
        int totalSubjects = 0;
        double totalAverage = 0.0;
        int passedSubjects = 0;
        
        for (int i = 0; i < records.getSize(); i++) {
            StudentGrade record = records.get(i);
            if (record.getStudentName().equals(studentName)) {
                totalSubjects++;
                double average = record.getAverageGrade();
                totalAverage += average;
                
                System.out.println("   " + record.getSubjectCode() + " (" + record.getSemester() + "): " +
                                 String.format("%.1f", average) + " - " + record.getLetterGrade() + 
                                 " [" + record.getStatus() + "]");
                
                if (record.isPassed()) {
                    passedSubjects++;
                }
            }
        }
        
        if (totalSubjects > 0) {
            double overallGPA = totalAverage / totalSubjects;
            System.out.println("   Overall GPA: " + String.format("%.2f", overallGPA));
            System.out.println("   Passed: " + passedSubjects + "/" + totalSubjects + " subjects");
        }
    }
}