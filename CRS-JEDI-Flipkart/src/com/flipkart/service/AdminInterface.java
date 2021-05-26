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
     */
    public void addCourse(Course course) throws CourseFoundException;

    /**
     * Method to remove course from course catalogue.
     * @param courseID -> ID of course which is to be removed
     */
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * Adds a professor object to the database
     * @param professor : professor object containing the details of the prof
     */
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException;

    /**
     * removes a professor object from the database
     * @param profID : professor ID of professor to be removed
     * @throws ProfFoundException
     */
    public void removeProf(String profID) throws ProfNotFoundException;

    /**
     * Adds student to the database
     * @param student -> student to be added
     * @throws UserAlreadyExistsException
     */
    public void addStudent(Student student) throws StudentAlreadyExistsException, StudentNotAddedException;

    /**
     * Remove student from database
     * @param studentID -> student which is to be removed
     * @throws UserNotFoundException
     */
    public void removeStudent(String studentID) throws StudentNotFoundException;

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
    public void generateReportCard(String studentID) throws StudentNotFoundException;

}
