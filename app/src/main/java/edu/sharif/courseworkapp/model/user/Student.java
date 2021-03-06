package edu.sharif.courseworkapp.model.user;

public class Student extends User {
    public static final String NAME = "Students";
    private String studentNumber;

    public Student(
            String username, String password, String firstname, String lastname, String studentNumber
    ) {
        super(username, password, firstname, lastname);
        this.studentNumber = studentNumber;
        this.type = User.STUDENT;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String encode() {
        return String.format("%s:%s:%s:%s", password, firstname, lastname, studentNumber);
    }
}
