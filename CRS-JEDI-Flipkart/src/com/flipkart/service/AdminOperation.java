package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.util.List;

public class AdminOperation implements AdminInterface{
    @Override
    public void addCourse(Course course) throws CourseFoundException {

    }

    @Override
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException {

    }

    @Override
    public void validateRegistration(String studentID, List<Student> registeredStudents) throws StudentNotRegisteredException{

    }

    @Override
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException{

    }

    @Override
    public void removeProf(String profID) throws ProfNotFoundException {

    }

    @Override
    public void addStudent(Student student) throws StudentAlreadyExistsException, StudentNotAddedException {

    }

    @Override
    public void removeStudent(String studentID) throws StudentNotFoundException {

    }

    @Override
    public void assignProf(String profID, int courseID) throws CourseNotFoundException, ProfNotFoundException{

    }

    @Override
    public void generateReportCard(String studentID) {

    }
}
