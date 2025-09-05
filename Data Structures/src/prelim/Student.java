package prelim;

public class Student {
    private String studentName;
    private String idNumber;
    private String status; // "ENROLLED", "NOT_ENROLLED", "DROPPED"
    private String department; // e.g., "Computer Science"
    private String course; // e.g., "Data Structures"
    private String yearLevel; // e.g., "1st Year"
    private double gpa;

    public Student(String studentName,
                   String idNumber,
                   String status,
                   String department,
                   String course,
                   String yearLevel,
                   double gpa) {
        this.studentName = studentName;
        this.idNumber = idNumber;
        this.status = status;
        this.department = department;
        this.course = course;
        this.yearLevel = yearLevel;
        this.gpa = gpa;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourse() {
        return course;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + studentName + '\'' +
                ", id='" + idNumber + '\'' +
                ", status='" + status + '\'' +
                ", dept='" + department + '\'' +
                ", course='" + course + '\'' +
                ", year='" + yearLevel + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}

