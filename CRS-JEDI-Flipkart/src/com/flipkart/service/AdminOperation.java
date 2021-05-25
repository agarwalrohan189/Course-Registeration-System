package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public class AdminOperation implements AdminInterface{
    @Override
    public void addCourse(Course course, List<Course> courseList) {

    }

    @Override
    public void removeCourse(int courseID, List<Course> courseList) {

    }

    @Override
    public void validateRegistration(String studentID, List<Student> registeredStudents) {

    }

    @Override
    public void addProf(Professor professor) {

    }

    @Override
    public void removeProf(Professor professor) {

    }

    @Override
    public void assignProf(String profID, int courseID) {

    }

    @Override
    public void generateReportCard(String studentID) {

    }
}
