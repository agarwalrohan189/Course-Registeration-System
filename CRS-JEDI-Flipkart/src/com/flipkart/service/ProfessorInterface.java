package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.List;

/**
 * Below interface describes operations performed by Admin.
 */
public interface ProfessorInterface {

    /**
     * Method to view course catalogue.
     * @return -> List of current courses in catalogue.
     */
    public List<Course> viewCourses();

    /**
     * Register for a course in a given semester.
     * @param courseID -> Course which professor is requesting.
     */
    public void registerCourse(int courseID);

    /**
     * Return enrolled student list for given course.
     * @param courseID -> ID of course whose students are requested.
     * @return -> List of students enrolled in course.
     */
    public List<Student> viewStudent(int courseID);

    /**
     * Method to assign grade to student.
     * @param studentID -> ID of student to whom grade will be assigned.
     * @param courseID -> ID of course in which grade is given.
     * @param grade -> Grade given to student.
     */
    public void assignGrade(String studentID, int courseID, String grade);

}
