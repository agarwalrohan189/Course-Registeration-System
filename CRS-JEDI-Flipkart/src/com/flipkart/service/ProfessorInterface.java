package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;

import java.util.List;

/**
 * Below interface describes operations performed by Admin.
 */
public interface ProfessorInterface {

    /**
     * Method to view course which professor is teaching
     * @return -> List of courses professor is teaching
     */
    public List<Course> viewCourses(String profID) throws ProfNotFoundException;

    /**
     * Return enrolled student list for given course.
     * @param courseID -> ID of course whose students are requested.
     * @return -> List of students enrolled in course.
     */
    public List<Student> viewStudent(int courseID, String profID) throws ProfNotFoundException;

    /**
     * Method to assign grade to student.
     * @param studentID -> ID of student to whom grade will be assigned.
     * @param courseID -> ID of course in which grade is given.
     * @param grade -> Grade given to student.
     */
    public void assignGrade(String profID, String studentID, int courseID, String grade) throws GradeNotAssignedException, ProfNotFoundException, StudentNotFoundException;

}
