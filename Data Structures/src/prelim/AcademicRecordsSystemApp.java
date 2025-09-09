package prelim;

import java.util.Scanner;

/**
 * Academic Records System Application
 * Demonstrates MyGrowingArrayList with StudentGrade objects
 * Perfect for expanding collections - Grows as more grades are added
 */
public class AcademicRecordsSystemApp {

    public static void main(String[] args) {
        System.out.println("ACADEMIC RECORDS SYSTEM (Interactive)");
        System.out.println("Using MyGrowingArrayList (Dynamic Growth)\n");

        MyGrowingArrayList<StudentGrade> academicRecords = new MyGrowingArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1) Add grade record");
            System.out.println("2) Search record (by name+subject+semester)");
            System.out.println("3) Remove record (by name+subject+semester)");
            System.out.println("4) Display all records");
            System.out.println("5) Analyze student performance");
            System.out.println("6) Trim memory to size");
            System.out.println("7) Exit");
            System.out.print("Enter choice (1-7): ");

            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    StudentGrade grade = promptGrade(scanner);
                    try {
                        academicRecords.insert(grade);
                    } catch (ListOverflowException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;
                case "2":
                    StudentGrade query = promptGradeKey(scanner);
                    try {
                        StudentGrade found = academicRecords.getElement(query);
                        System.out.println("Found record: " + found);
                    } catch (Exception e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;
                case "3":
                    StudentGrade key = promptGradeKey(scanner);
                    boolean removed = academicRecords.delete(key);
                    if (!removed) {
                        System.out.println("Record not found.");
                    }
                    break;
                case "4":
                    academicRecords.display();
                    System.out.println("FINAL SYSTEM STATISTICS:");
                    System.out.println("============================");
                    System.out.println("Total grade records: " + academicRecords.getSize());
                    System.out.println("Current capacity: " + academicRecords.getCapacity());
                    System.out.println("Memory efficiency: " +
                            String.format("%.1f%%", (double) academicRecords.getSize() / academicRecords.getCapacity() * 100));
                    System.out.println("Is empty: " + academicRecords.isEmpty());
                    break;
                case "5":
                    System.out.print("Enter student name to analyze: ");
                    String name = scanner.nextLine().trim();
                    analyzeStudentPerformance(academicRecords, name);
                    break;
                case "6":
                    System.out.println("Before optimization - Capacity: " + academicRecords.getCapacity());
                    academicRecords.trimToSize();
                    System.out.println("After optimization - Capacity: " + academicRecords.getCapacity());
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number 1-7.");
            }
            System.out.println();
        }

        System.out.println("Goodbye.");
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

    private static StudentGrade promptGrade(Scanner scanner) {
        System.out.println("Enter grade record details:");
        System.out.print("Student name: ");
        String studentName = scanner.nextLine().trim();

        System.out.print("Subject code (e.g., ICS211): ");
        String subjectCode = scanner.nextLine().trim();

        System.out.print("Semester (e.g., 1st Semester 2024): ");
        String semester = scanner.nextLine().trim();

        double midterm = promptDouble(scanner, "Midterm grade: ");
        double fin = promptDouble(scanner, "Final grade: ");

        System.out.print("Letter grade (e.g., A, B+, C): ");
        String letter = scanner.nextLine().trim();

        System.out.print("Status (PASSED/FAILED/INCOMPLETE): ");
        String status = scanner.nextLine().trim();

        return new StudentGrade(studentName, subjectCode, semester, midterm, fin, letter, status);
    }

    private static StudentGrade promptGradeKey(Scanner scanner) {
        System.out.println("Enter record key (name + subject + semester):");
        System.out.print("Student name: ");
        String studentName = scanner.nextLine().trim();
        System.out.print("Subject code: ");
        String subjectCode = scanner.nextLine().trim();
        System.out.print("Semester: ");
        String semester = scanner.nextLine().trim();
        StudentGrade key = new StudentGrade();
        key.setStudentName(studentName);
        key.setSubjectCode(subjectCode);
        key.setSemester(semester);
        return key;
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