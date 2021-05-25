package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.GradeNotAssignedException;

import java.util.List;

public class ProfessorOperation implements ProfessorInterface{

    @Override
    public List<Course> viewCourses(String profID) {
        return null;
    }

    @Override
    public List<Student> viewStudent(int courseID, String profID) {
        return null;
    }

    @Override
    public void assignGrade(String profID, String studentID, int courseID, String grade) throws GradeNotAssignedException {

    }
}
