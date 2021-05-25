package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public class ProfessorOperation implements ProfessorInterface{
    @Override
    public List<Course> viewCourses() {
        return null;
    }

    @Override
    public void registerCourse(int courseID) {

    }

    @Override
    public List<Student> viewStudent(int courseID) {
        return null;
    }

    @Override
    public void assignGrade(String studentID, int courseID, String grade) {

    }
}
