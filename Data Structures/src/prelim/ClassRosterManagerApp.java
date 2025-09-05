package prelim;

/**
 * Class Roster Manager Application
 * Demonstrates MyFixedSizeArrayList with Student objects
 * Perfect for limited capacity scenarios - Maximum 5 students per section
 */
public class ClassRosterManagerApp {
    
    public static void main(String[] args) {
        System.out.println("CLASS ROSTER MANAGER DEMO");
        System.out.println("Using MyFixedSizeArrayList (Fixed Size = 5)\n");
        
        // Create a fixed-size roster for a class section
        MyFixedSizeArrayList<Student> classRoster = new MyFixedSizeArrayList<>();
        
        // Create sample students
        Student student1 = new Student("Alice Johnson", "2021001", "ENROLLED", 
                                     "Computer Science", "Data Structures", "2nd Year", 3.8);
        Student student2 = new Student("Bob Smith", "2021002", "ENROLLED", 
                                     "Computer Science", "Data Structures", "2nd Year", 3.5);
        Student student3 = new Student("Carol Davis", "2021003", "ENROLLED", 
                                     "Engineering", "Data Structures", "2nd Year", 3.9);
        Student student4 = new Student("David Wilson", "2021004", "ENROLLED", 
                                     "Computer Science", "Data Structures", "2nd Year", 3.2);
        Student student5 = new Student("Eva Brown", "2021005", "ENROLLED", 
                                     "Engineering", "Data Structures", "2nd Year", 3.7);
        Student student6 = new Student("Frank Miller", "2021006", "NOT_ENROLLED", 
                                     "Computer Science", "Data Structures", "2nd Year", 3.4);
        
        try {
            System.out.println("ENROLLING STUDENTS:");
            System.out.println("======================");
            
            // Add students to the roster
            classRoster.insert(student1);
            classRoster.insert(student2);
            classRoster.insert(student3);
            classRoster.insert(student4);
            classRoster.insert(student5);
            
            // Display current roster
            classRoster.display();
            
            // Try to add one more student (this should throw an exception)
            System.out.println("ATTEMPTING TO EXCEED CAPACITY:");
            System.out.println("===================================");
            classRoster.insert(student6);
            
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Cannot enroll more students - section is full!\n");
        }
        
        // Demonstrate search functionality
        System.out.println("SEARCHING FOR STUDENTS:");
        System.out.println("===========================");
        try {
            Student foundStudent = classRoster.getElement(student2);
            System.out.println("Found: " + foundStudent.getStudentName());
            
            // Search by creating a student with same ID (equals method uses ID)
            Student searchStudent = new Student();
            searchStudent.setIdNumber("2021003");
            Student found = classRoster.getElement(searchStudent);
            System.out.println("Found by ID: " + found.getStudentName());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Demonstrate deletion
        System.out.println("\nDROPPING A STUDENT:");
        System.out.println("========================");
        boolean deleted = classRoster.delete(student3);
        if (deleted) {
            System.out.println("Successfully dropped " + student3.getStudentName());
        }
        
        classRoster.display();
        
        // Now we can add the waitlisted student
        try {
            System.out.println("ENROLLING FROM WAITLIST:");
            System.out.println("============================");
            student6.setStatus("ENROLLED");
            classRoster.insert(student6);
            classRoster.display();
            
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
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
    }
}