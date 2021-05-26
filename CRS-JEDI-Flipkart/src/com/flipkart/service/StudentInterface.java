/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.StudentNotFoundException;

import java.util.List;

public interface StudentInterface {
	
	
    public List<Course> viewCourseCatalogue();

    public List<RegisteredCourse> viewGrades(String StudentId) throws StudentNotFoundException;

} 
