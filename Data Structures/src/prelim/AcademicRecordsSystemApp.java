package prelim;

/**
 * Academic Records System Application
 * Demonstrates MyGrowingArrayList with StudentGrade objects
 * Perfect for expanding collections - Grows as more grades are added
 */
public class AcademicRecordsSystemApp {
    
    public static void main(String[] args) {
        System.out.println("ACADEMIC RECORDS SYSTEM DEMO");
        System.out.println("Using MyGrowingArrayList (Dynamic Growth)\n");
        
        // Create a growing list for academic records
        MyGrowingArrayList<StudentGrade> academicRecords = new MyGrowingArrayList<>();
        
        // Create sample grade records for different students and subjects
        StudentGrade[] grades = {
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
            
            // Search for a specific grade record
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
            
            System.out.println("\nANALYZING ACADEMIC PERFORMANCE:");
            System.out.println("===================================");
            
            // Analyze grades for each student
            analyzeStudentPerformance(academicRecords, "Alice Johnson");
            analyzeStudentPerformance(academicRecords, "Bob Smith");
            analyzeStudentPerformance(academicRecords, "Carol Davis");
            
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