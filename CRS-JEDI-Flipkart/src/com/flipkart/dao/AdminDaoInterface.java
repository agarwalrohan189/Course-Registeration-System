package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Below interface describes operations performed by Admin.
 */
public interface AdminDaoInterface {

    /**
     * Method to add course in catalogue using SQL command.
     * @param course -> Course to be added
     * @throws CourseFoundException
     */
    public void addCourse(Course course) throws CourseFoundException;

    /**
     * Method to remove course from course catalogue using SQL command.
     * @param courseID -> ID of course which is to be removed
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * To validate course registration of a student using SQL command.
     * @param studentID -> ID of student to be added
     * @param registeredStudents -> List of registered students in the course
     * @throws StudentNotRegisteredException
     * @throws StudentNotFoundException
     */
    public void validateRegistration(String studentID, List<Student> registeredStudents) throws StudentNotRegisteredException, StudentNotFoundException;

    /**
     * Adds a professor object to the database using SQL command
     * @param professor : professor object containing the details of the prof
     * @throws ProfNotAddedException
     * @throws ProfFoundException
     */
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException;

    /**
     * Adds a user object to the database using SQL command
     * @param user : user object containing the details of the user
     */
    public void addUser(User user) throws UserNotAddedException;

    /**
     * removes a professor object from the database using SQL command
     * @param profID : professor ID of professor to be removed
     * @throws ProfFoundException
     */
    public void removeProf(String profID) throws ProfNotFoundException, ProfNotDeletedException;

    /**
     * Remove student from database using SQL command
     * @param studentID -> student which is to be removed
     * @throws UserNotFoundException
     */
    public void removeStudent(String studentID) throws StudentNotFoundException;

    /**
     * Add professor as instructor in the given course using SQL command.
     * @param profID -> ID of professor to be added
     * @param courseID -> ID of course which professor is requesting
     */
    public void assignProf(String profID, int courseID) throws CourseNotFoundException, ProfNotFoundException;
    
    public HashMap<String,List<Integer>> getPreferredCourses() throws DatabaseException;
    public HashMap<String,List<Integer>> getAlternateCourses() throws DatabaseException;

    /**
     * Method to generate Report card of student using SQL command.
     * @param studentID -> ID of student whose report card is being generated
     */
    public void generateReportCard(String studentID) throws StudentNotFoundException;

    /**
     * Method to get list of courses in catalog
     * @return List of courses in CourseCatalogue
     */
    public List<Course> viewCourses();

    /**
     * For deleting the chosen courses
     * @throws SQLException
     */
    public void deleteChosenCourses() throws SQLException;

    /**
     * Method to ensure no course has less than three registered students
     */
    public void validateRegistration() throws CourseNotDeletedException;

    /**
     * Method to approve Student
     * @param studentId -> ID of student to be approved
     * @throws StudentNotFoundException
     */
    public void approveStudent(String studentId) throws StudentNotFoundException;

}
