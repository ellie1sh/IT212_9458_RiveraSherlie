package prelim;

/**
 * Student class representing a student in the registration system.
 * Perfect for limited capacity scenarios with MyFixedSizeArrayList.
 */
public class Student {
    private String studentName;
    private String idNumber;
    private String status;        // "ENROLLED", "NOT_ENROLLED", "DROPPED"
    private String department;    // "Computer Science", "Engineering", etc.
    private String course;        // "Data Structures", "Programming", etc.
    private String yearLevel;     // "1st Year", "2nd Year", etc.
    private double gpa;

    /**
     * Default constructor
     */
    public Student() {
        this.studentName = "";
        this.idNumber = "";
        this.status = "NOT_ENROLLED";
        this.department = "";
        this.course = "";
        this.yearLevel = "";
        this.gpa = 0.0;
    }

    /**
     * Parameterized constructor
     */
    public Student(String studentName, String idNumber, String status, 
                   String department, String course, String yearLevel, double gpa) {
        this.studentName = studentName;
        this.idNumber = idNumber;
        this.status = status;
        this.department = department;
        this.course = course;
        this.yearLevel = yearLevel;
        this.gpa = gpa;
    }

    // Getters and Setters
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getYearLevel() { return yearLevel; }
    public void setYearLevel(String yearLevel) { this.yearLevel = yearLevel; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return idNumber.equals(student.idNumber);
    }

    @Override
    public int hashCode() {
        return idNumber.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', id='%s', status='%s', dept='%s', course='%s', year='%s', gpa=%.2f}",
                studentName, idNumber, status, department, course, yearLevel, gpa);
    }
}