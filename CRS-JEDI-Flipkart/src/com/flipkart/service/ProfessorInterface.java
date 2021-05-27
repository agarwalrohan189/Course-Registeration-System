package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.Grade;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;

import java.util.List;

/**
 * Below interface describes operations performed by Professor.
 */
public interface ProfessorInterface {

	/**
     * Method to view course which professor is teaching
     * @param profID -> id of professor who is viewing courses
     * @return -> List of courses professor is teaching
     * @throws ProfNotFoundException
     */
    public List<Course> viewCourses(String profID) throws ProfNotFoundException;

    /**
     * Return enrolled student list for given course.
     * @param courseID -> ID of course whose students are requested.
     * @param profID -> ID of professor viewing students
     * @return -> List of students enrolled in course.
     * @throws ProfNotFoundException
     * @throws StudentNotFoundException
     */
    public List<Student> viewStudent(int courseID, String profID) throws ProfNotFoundException, StudentNotFoundException;

    /**
     * Method to assign grade to student.
     * @param studentID -> ID of student to whom grade will be assigned.
     * @param courseID -> ID of course in which grade is given.
     * @param grade -> Grade given to student.
     * @throws GradeNotAssignedException
     * @throws StudentNotFoundException
     */
    public void assignGrade(String studentID, int courseID, Grade grade) throws GradeNotAssignedException, StudentNotFoundException;

}
