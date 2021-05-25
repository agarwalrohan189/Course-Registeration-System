/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;

import java.util.List;

public interface StudentInterface {
    public int register(String name,String userID,String password,Gender gender,int batch,String branch,String address,String country);

    public List<Course> viewCourseCatalogue(String userID);

    public List<StudentGrades> viewGrades(String userID);

    public int getStudentId(String userId);
}
