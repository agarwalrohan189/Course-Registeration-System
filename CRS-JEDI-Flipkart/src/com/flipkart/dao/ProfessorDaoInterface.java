package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.GradeConstant;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;

import java.util.List;

/**
 * Dao interface for professor operations
 */
public interface ProfessorDaoInterface {

    /**
     * Method to view course which professor is teaching
     * @return -> List of courses professor is teaching
     */
    public List<Course> viewCourses(String profID) throws  ProfNotFoundException;

    /**
     * Return enrolled student list for given course.
     * @param courseID -> ID of course whose students are requested.
     * @return -> List of students enrolled in course.
     */
    public List<Student> viewStudent(int courseID, String profID) throws ProfNotFoundException, StudentNotFoundException;

    /**
     * Method to assign grade to student.
     * @param studentID -> ID of student to whom grade will be assigned.
     * @param courseID -> ID of course in which grade is given.
     * @param grade -> Grade given to student.
     */
    public void assignGrade(String studentID, int courseID, GradeConstant grade) throws StudentNotFoundException, GradeNotAssignedException;
}
