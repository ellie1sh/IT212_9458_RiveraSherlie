package prelim;

public class StudentGrade {
    private String studentName;
    private String subjectCode; // e.g., "ICS211"
    private String semester; // e.g., "1st Semester 2024"
    private double midtermGrade;
    private double finalGrade;
    private String letterGrade; // e.g., "A", "B+"
    private String status; // e.g., "PASSED", "FAILED", "INCOMPLETE"
    private String dateRecorded;

    public StudentGrade(String studentName,
                        String subjectCode,
                        String semester,
                        double midtermGrade,
                        double finalGrade,
                        String letterGrade,
                        String status,
                        String dateRecorded) {
        this.studentName = studentName;
        this.subjectCode = subjectCode;
        this.semester = semester;
        this.midtermGrade = midtermGrade;
        this.finalGrade = finalGrade;
        this.letterGrade = letterGrade;
        this.status = status;
        this.dateRecorded = dateRecorded;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSemester() {
        return semester;
    }

    public double getMidtermGrade() {
        return midtermGrade;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public String getStatus() {
        return status;
    }

    public String getDateRecorded() {
        return dateRecorded;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "student='" + studentName + '\'' +
                ", subject='" + subjectCode + '\'' +
                ", semester='" + semester + '\'' +
                ", midterm=" + midtermGrade +
                ", final=" + finalGrade +
                ", letter='" + letterGrade + '\'' +
                ", status='" + status + '\'' +
                ", date='" + dateRecorded + '\'' +
                '}';
    }
}

