package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.exception.UserNotFoundException;

import java.util.List;

public class AdminOperation implements AdminInterface{
    @Override
    public void addCourse(Course course) {

    }

    @Override
    public void removeCourse(int courseID) {

    }

    @Override
    public void validateRegistration(String studentID, List<Student> registeredStudents) {

    }

    @Override
    public void addProf(Professor professor) {

    }

    @Override
    public void removeProf(String profID) throws ProfNotFoundException {

    }

    @Override
    public void addStudent(Student student) throws UserAlreadyExistsException {

    }

    @Override
    public void removeStudent(String studentID) throws UserNotFoundException {

    }

    @Override
    public void assignProf(String profID, int courseID) {

    }

    @Override
    public void generateReportCard(String studentID) {

    }
}
