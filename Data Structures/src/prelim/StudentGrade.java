package prelim;

/**
 * StudentGrade class representing a student's grade record.
 * Perfect for expanding collections with MyGrowingArrayList.
 */
public class StudentGrade {
    private String studentName;
    private String subjectCode;     // "ICS211", "MATH101", etc.
    private String semester;        // "1st Semester 2024"
    private double midtermGrade;
    private double finalGrade;
    private String letterGrade;     // "A", "B+", "C", etc.
    private String status;          // "PASSED", "FAILED", "INCOMPLETE"

    /**
     * Default constructor
     */
    public StudentGrade() {
        this.studentName = "";
        this.subjectCode = "";
        this.semester = "";
        this.midtermGrade = 0.0;
        this.finalGrade = 0.0;
        this.letterGrade = "";
        this.status = "INCOMPLETE";
    }

    /**
     * Parameterized constructor
     */
    public StudentGrade(String studentName, String subjectCode, String semester,
                        double midtermGrade, double finalGrade, String letterGrade,
                        String status) {
        this.studentName = studentName;
        this.subjectCode = subjectCode;
        this.semester = semester;
        this.midtermGrade = midtermGrade;
        this.finalGrade = finalGrade;
        this.letterGrade = letterGrade;
        this.status = status;
    }

    // Getters and Setters
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public double getMidtermGrade() { return midtermGrade; }
    public void setMidtermGrade(double midtermGrade) { this.midtermGrade = midtermGrade; }

    public double getFinalGrade() { return finalGrade; }
    public void setFinalGrade(double finalGrade) { this.finalGrade = finalGrade; }

    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    /**
     * Calculate the average grade from midterm and final
     */
    public double getAverageGrade() {
        return (midtermGrade + finalGrade) / 2.0;
    }

    /**
     * Determine if the student passed based on average grade
     */
    public boolean isPassed() {
        return getAverageGrade() >= 75.0; // Assuming 75 is passing grade
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentGrade grade = (StudentGrade) obj;
        return studentName.equals(grade.studentName) && 
               subjectCode.equals(grade.subjectCode) && 
               semester.equals(grade.semester);
    }

    @Override
    public int hashCode() {
        return (studentName + subjectCode + semester).hashCode();
    }

    @Override
    public String toString() {
        return String.format("StudentGrade{name='%s', subject='%s', semester='%s', " +
                "midterm=%.2f, final=%.2f, letter='%s', status='%s'}",
                studentName, subjectCode, semester, midtermGrade, finalGrade, 
                letterGrade, status);
    }
}