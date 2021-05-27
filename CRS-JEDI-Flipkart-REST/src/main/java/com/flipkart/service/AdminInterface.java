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
     * @throws CourseFoundException
     */
    public void addCourse(Course course) throws CourseFoundException;

    /**
     * Method to remove course from course catalogue.
     * @param courseID -> ID of course which is to be removed
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * Adds a professor object to the database
     * @param professor : professor object containing the details of the prof
     * @throws ProfNotAddedException
     * @throws ProfFoundException
     */
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException;

    /**
     * removes a professor object from the database
     * @param profID : professor ID of professor to be removed
     * @throws ProfFoundException
     * @throws ProfNotDeletedException
     */
    public void removeProf(String profID) throws ProfNotFoundException, ProfNotDeletedException;

    /**
     * Remove student from database
     * @param studentID -> student which is to be removed
     * @throws StudentNotFoundException
     */
    public void removeStudent(String studentID) throws StudentNotFoundException;

    /**
     * Add professor as instructor in the given course.
     * @param profID -> ID of professor to be added
     * @param courseID -> ID of course which professor is requesting
     * @throws CourseNotFoundException
     * @throws ProfNotFoundException
     */
    public void assignProf(String profID, int courseID) throws CourseNotFoundException, ProfNotFoundException;
    
    /**
     * Method to generate Report card of student.
     * @param studentID -> ID of student whose report card is being generated
     * @throws StudentNotFoundException
     */
    public void generateReportCard(String studentID) throws StudentNotFoundException;

    /**
     * Method to get list of courses in catalog
     * @return List of courses in CourseCatalogue
     */
    public List<Course> viewCourses();

    /**
     * Method to ensure no course has less than three registered students
     * @throws DatabaseException
     */
    public void validateRegistration() throws DatabaseException;

    /**
     * Method to approve Student
     * @param studentId -> ID of student to be approved
     * @throws StudentNotFoundException
     */
    public void approveStudent(String studentId) throws StudentNotFoundException;
    
    /**
     * Method to accept scholarship from students
     * @param studentID
     */
    public void paymentDoneViaScholarship(String studentID);
    
    /**
     * Method to accept Demand Draft from students
     * @param studentID
     */
    public void paymentDoneViaDemandDraft(String studentID);

    /**
     * Method to fetch the list of all enrolled professors
     * @return List of Professors in table Professors
     */
    public List<Professor> viewProfessors();

    /**
     * Method view pending approvals of students
     * @return list of student objects whose approvals are pending
     */
    public List<Student> viewPending();
}
