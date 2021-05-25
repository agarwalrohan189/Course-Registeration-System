/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.List;

public interface StudentInterface {
    public int register(String name,String userID,String password,String gender,int batch,String branch,String address,String country);

    public List<Course> viewCourseCatalogue(String userID);

    public List<StudentGrade> viewGrades(String userID);

    public int getStudentId(String userId);
}
