package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

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
    public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;

    /**
     * Method to remove course from course catalogue.
     * @param courseID -> ID of course which is to be removed
     * @param courseList -> Current course catalogue
     */
    public void removeCourse(int courseID, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * To validate course registration of a student.
     * @param studentID -> ID of student to be added
     * @param registeredStudents -> List of registered students in the course
     */
    public void validateRegistration(String studentID, List<Student> registeredStudents);

    /**
     * Adds a professor object to the database
     * @param professor : professor object containing the details of the prof
     */
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException;

    /**
     * removes a professor object from the database
     * @param professor : professor object containing the details of the prof
     */
    public void removeProf(Professor professor) throws ProfNotFoundException;

    /**
     * Add professor as instructor in the given course.
     * @param profID -> ID of professor to be added
     * @param courseID -> ID of course which professor is requesting
     */
    public void assignProf(String profID, int courseID) throws CourseNotFoundException, ProfNotFoundException;

    /**
     * Method to generate Report card of student.
     * @param studentID -> ID of student whose report card is being generated
     */
    public void generateReportCard(String studentID);

}
