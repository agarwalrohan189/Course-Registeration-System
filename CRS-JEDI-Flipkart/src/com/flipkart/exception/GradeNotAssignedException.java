package com.flipkart.exception;

public class GradeNotAssignedException extends Exception{
    private String studentID;

    public GradeNotAssignedException(String studentID){
        this.studentID = studentID;
    }

    @Override
    public String getMessage() {
        return "Grade not assigned to student with ID " + studentID;
    }

    public String getStudentID(){
        return this.studentID;
    }

}
