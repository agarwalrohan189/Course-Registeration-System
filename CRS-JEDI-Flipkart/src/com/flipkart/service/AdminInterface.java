package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * Below interface describes operations performed by Admin.
 */
public interface AdminInterface {

    /**
     * Method to add course in catalogue.
     * @param course -> Course to be added
     * @param courseList -> Current course Catalogue
     */
    public void addCourse(Course course, List<Course> courseList);

    /**
     * Method to remove course from course catalogue.
     * @param courseID -> ID of course which is to be removed
     * @param courseList -> Current course catalogue
     */
    public void removeCourse(int courseID, List<Course> courseList);

    /**
     * To validate course registration of a student.
     * @param studentID -> ID of student to be added
     * @param registeredStudents -> List of registered students in the course
     */
    public void validateRegistration(String studentID, List<Student> registeredStudents);

    /**
     * Add professor as instructor in the given course.
     * @param profID -> ID of professor to be added
     * @param courseID -> ID of course which professor is requesting
     */
    public void addProf(String profID, int courseID);

    /**
     * Method to generate Report card of student.
     * @param studentID -> ID of student whose report card is being generated
     */
    public void generateReportCard(String studentID);

}
