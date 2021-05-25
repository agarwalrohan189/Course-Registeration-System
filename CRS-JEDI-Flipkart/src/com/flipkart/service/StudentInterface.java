/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.StudentNotRegisteredException;

import java.util.Date;
import java.util.List;

public interface StudentInterface {
    public void register(String id, String name, String role, String password, String gender, String address, String username,
			Date doB, String branch, Course[] coursesEnrolled, int batchYear) throws StudentNotRegisteredException;

    public List<Course> viewCourseCatalogue(String userID);

    public List<StudentGrade> viewGrades(String userID);

    public int getStudentId(String userId);
}
