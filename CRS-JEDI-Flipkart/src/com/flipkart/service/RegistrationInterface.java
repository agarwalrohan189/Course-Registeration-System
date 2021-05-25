/**
 * 
 */
package com.flipkart.service;


import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.*;

public interface RegistrationInterface {

    public void registerCourses(String studentId);

    public boolean addCourse(String studentId, int courseCode) throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException;

    public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException;

    // public List<Course> viewCourses(String studentId);

    public List<Course> viewRegisteredCourses(String studentId);

    public List<StudentGrade> viewGradeCard(String studentId);

    public double calculateFee(String studentId);

    public boolean isRegistered(String studentId);
}
