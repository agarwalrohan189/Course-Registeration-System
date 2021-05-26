package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterfaceImpl;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.util.List;

public class AdminOperation implements AdminInterface{

    private static volatile AdminOperation instance = null;

    private AdminOperation(){}

    public static AdminOperation getInstance() {
        if (instance == null) {
            synchronized (AdminOperation.class) {
                instance = new AdminOperation();
            }
        }
        return instance;
    }

    AdminDaoInterfaceImpl adminDaoInterfaceImpl = AdminDaoInterfaceImpl.getInstance();

    @Override
    public void addCourse(Course course) throws CourseFoundException {
        try{
            adminDaoInterfaceImpl.addCourse(course);
        } catch (CourseFoundException e) {
            throw e;
        }
    }

    @Override
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException {
        try{
            adminDaoInterfaceImpl.removeCourse(courseID);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException{
        try{
            adminDaoInterfaceImpl.addProf(professor);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void removeProf(String profID) throws ProfNotFoundException {
        try{
            adminDaoInterfaceImpl.removeProf(profID);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void addStudent(Student student) throws StudentAlreadyExistsException, StudentNotAddedException {
        try{
            adminDaoInterfaceImpl.addStudent(student);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void removeStudent(String studentID) throws StudentNotFoundException {
        try{
            adminDaoInterfaceImpl.removeStudent(studentID);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void assignProf(String profID, int courseID) throws CourseNotFoundException, ProfNotFoundException{
        try{
            adminDaoInterfaceImpl.assignProf(profID, courseID);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generateReportCard(String studentID) throws StudentNotFoundException {
        try {
            adminDaoInterfaceImpl.generateReportCard(studentID);
        } catch (StudentNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void validateRegistration() {

    }
}
