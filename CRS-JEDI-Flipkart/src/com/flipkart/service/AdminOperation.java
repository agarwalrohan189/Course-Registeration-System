package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterfaceImpl;
import com.flipkart.exception.*;
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
        try {
            adminDaoInterfaceImpl.removeProf(profID);
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

    /**
     * Method to get list of courses in catalog
     * @return List of courses in CourseCatalogue
     */
    @Override
    public List<Course> viewCourses() {
        List<Course> courseList = null;
        try {
            courseList = adminDaoInterfaceImpl.viewCourses();
        } catch (Exception e) {
            throw e;
        }
        return courseList;
    }

    /**
     * Method to ensure no course has less than three registered students
     */
    @Override
    public void validateRegistration() throws CourseNotDeletedException{
        try {
            adminDaoInterfaceImpl.validateRegistration();
        } catch (CourseNotDeletedException e) {
            throw e;
        }
    }

    /**
     * Method to approve Student
     * @param studentId -> ID of student to be approved
     * @throws StudentNotFoundException
     */
    @Override
    public void approveStudent(String studentId) throws StudentNotFoundException {
        try {
            adminDaoInterfaceImpl.approveStudent(studentId);
        } catch (Exception e) {
            throw e;
        }
    }

}
