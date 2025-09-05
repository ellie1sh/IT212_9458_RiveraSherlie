package prelim;
public class SimpleTestApp {
    public static void main(String[] args) {
        System.out.println("=== Testing All Data Structures ===\n");
// Test 1: Fixed Size Array List
        System.out.println("1. Testing MyFixedSizeArrayList:");
        testFixedSizeArrayList();
        System.out.println("\n1a. Testing MyFixedSizeArrayList with Student objects:");
        testFixedSizeArrayListStudents();
// Test 2: Growing Array List
        System.out.println("\n2. Testing MyGrowingArrayList:");
        testGrowingArrayList();
        System.out.println("\n2a. Testing MyGrowingArrayList with StudentGrade objects:");
        testGrowingArrayListStudentGrades();
// Test 3: Singly Linked List
        System.out.println("\n3. Testing MySinglyLinkedList:");
        testSinglyLinkedList();
// Test 4: Doubly Linked List

        System.out.println("\n4. Testing MyDoublyLinkedList:");
        testDoublyLinkedList();
// Test 5: Singly Linked Circular List
        System.out.println("\n5. Testing MySinglyLinkedCircularList:");
        testSinglyLinkedCircularList();
// Test 6: Doubly Linked Circular List
        System.out.println("\n6. Testing MyDoublyLinkedCircularList:");
        testDoublyLinkedCircularList();
    }
    private static void testFixedSizeArrayList() {
        MyFixedSizeArrayList<String> list = new MyFixedSizeArrayList<>();
        try {
            list.insert("Apple");
            list.insert("Banana");
            list.insert("Cherry");
            list.display();
            System.out.println("Searching for 'Banana': Index " + list.search("Banana"));
            list.delete("Banana");
            System.out.println("After deleting 'Banana':");
            list.display();
// Try to fill the list
            list.insert("Date");
            list.insert("Elderberry");
            list.insert("Fig"); // This should cause overflow
        } catch (ListOverflowException e) {
            System.out.println("Overflow caught: " + e.getMessage());
        }
        System.out.println("Final state:");
        list.display();
    }
    private static void testFixedSizeArrayListStudents() {
        MyFixedSizeArrayList<Student> roster = new MyFixedSizeArrayList<>();
        try {
            Student s1 = new Student("John Doe", "2024-0001", "ENROLLED", "Computer Science", "Data Structures", "1st Year", 3.25);
            Student s2 = new Student("Jane Smith", "2024-0002", "ENROLLED", "Computer Science", "Programming", "1st Year", 3.50);
            Student s3 = new Student("Alex Lee", "2024-0003", "ENROLLED", "Engineering", "Circuits", "2nd Year", 2.95);
            Student s4 = new Student("Maria Cruz", "2024-0004", "ENROLLED", "Computer Science", "Algorithms", "2nd Year", 3.75);
            Student s5 = new Student("Sam Park", "2024-0005", "ENROLLED", "Engineering", "Statics", "3rd Year", 3.10);
            Student s6 = new Student("Liam Reyes", "2024-0006", "ENROLLED", "Computer Science", "Operating Systems", "2nd Year", 3.40);

            roster.insert(s1);
            roster.insert(s2);
            roster.insert(s3);
            roster.display();
            System.out.println("Searching for Jane Smith: Index " + roster.search(s2));
            roster.delete(s2);
            System.out.println("After deleting Jane Smith:");
            roster.display();
            roster.insert(s4);
            roster.insert(s5);
            // This insert should exceed the fixed capacity (5)
            roster.insert(s6);
        } catch (ListOverflowException e) {
            System.out.println("Overflow caught (roster): " + e.getMessage());
        }
        System.out.println("Final roster state:");
        roster.display();
    }
    private static void testGrowingArrayList() {
        MyGrowingArrayList<Integer> list = new MyGrowingArrayList<>();
        try {
// Insert more than initial capacity to test growing
            for (int i = 1; i <= 8; i++) {
                list.insert(i * 10);
                System.out.println("Inserted " + (i * 10));
            }
            list.display();
            System.out.println("Size: " + list.getSize());
            list.delete(30);
            System.out.println("After deleting 30:");
            list.display();
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    private static void testGrowingArrayListStudentGrades() {
        MyGrowingArrayList<StudentGrade> grades = new MyGrowingArrayList<>();
        try {
            StudentGrade g1 = new StudentGrade("John Doe", "ICS211", "1st Semester 2024", 91.5, 94.0, "A", "PASSED", "2024-06-01");
            StudentGrade g2 = new StudentGrade("Jane Smith", "MATH101", "1st Semester 2024", 86.0, 88.5, "B+", "PASSED", "2024-06-02");
            StudentGrade g3 = new StudentGrade("Alex Lee", "ENGR200", "1st Semester 2024", 70.0, 74.0, "C", "PASSED", "2024-06-03");
            StudentGrade g4 = new StudentGrade("Maria Cruz", "ICS212", "1st Semester 2024", 92.0, 95.0, "A", "PASSED", "2024-06-04");
            StudentGrade g5 = new StudentGrade("Sam Park", "CHEM101", "1st Semester 2024", 65.0, 69.0, "D+", "PASSED", "2024-06-05");
            StudentGrade g6 = new StudentGrade("Liam Reyes", "PHYS101", "1st Semester 2024", 88.0, 90.0, "A-", "PASSED", "2024-06-06");
            StudentGrade g7 = new StudentGrade("Ivy Tan", "HIST100", "1st Semester 2024", 78.0, 82.0, "B-", "PASSED", "2024-06-07");
            StudentGrade g8 = new StudentGrade("Noah Kim", "PSY101", "1st Semester 2024", 83.0, 85.0, "B", "PASSED", "2024-06-08");

            grades.insert(g1);
            grades.insert(g2);
            grades.insert(g3);
            grades.insert(g4);
            grades.insert(g5);
            grades.insert(g6); // triggers growth beyond initial capacity (5)
            grades.insert(g7);
            grades.insert(g8);

            grades.display();
            System.out.println("Grade records size: " + grades.getSize());
            grades.delete(g3);
            System.out.println("After deleting Alex Lee's ENGR200 grade:");
            grades.display();
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void testSinglyLinkedList() {
        MySinglyLinkedList<String> list = new MySinglyLinkedList<>();
        try {
            list.insert("First");
            list.insert("Second");
            list.insert("Third");
            list.insert("Fourth");
            list.display();
            System.out.println("Searching for 'Third': Index " + list.search("Third"));
            list.delete("Second");
            System.out.println("After deleting 'Second':");
            list.display();
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void testDoublyLinkedList() {
        MyDoublyLinkedList<Character> list = new MyDoublyLinkedList<>();
        try {
            list.insert('A');
            list.insert('B');
            list.insert('C');
            list.insert('D');
            list.display();
            System.out.println("Searching for 'C': Index " + list.search('C'));
            list.delete('B');
            System.out.println("After deleting 'B':");
            list.display();
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void testSinglyLinkedCircularList() {
        MySinglyLinkedCircularList<String> list = new MySinglyLinkedCircularList<>();
        try {
            list.insert("Monday");
            list.insert("Tuesday");
            list.insert("Wednesday");
            list.insert("Thursday");
            list.display();
            System.out.println("Searching for 'Wednesday': Index " + list.search("Wednesday"));
            list.delete("Tuesday");
            System.out.println("After deleting 'Tuesday':");
            list.display();
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
    private static void testDoublyLinkedCircularList() {
        MyDoublyLinkedCircularList<Integer> list = new MyDoublyLinkedCircularList<>();
        try {
            list.insert(100);
            list.insert(200);
            list.insert(300);
            list.insert(400);
            list.display();
            System.out.println("Searching for 300: Index " + list.search(300));
            list.delete(200);
            System.out.println("After deleting 200:");
            list.display();
            System.out.println("Final size: " + list.getSize());
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}