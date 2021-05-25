/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;

import java.util.Date;
import java.util.List;

public interface StudentInterface {
    public void register(String studentId, String name, String role, String password, String gender, String address, String username,
			Date doB, String branch, Course[] coursesEnrolled, int batchYear) throws StudentNotRegisteredException;

    public List<Course> viewCourseCatalogue();

    public List<StudentGrade> viewGrades(String StudentId) throws UserNotFoundException;

} 
