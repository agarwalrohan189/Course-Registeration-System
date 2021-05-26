/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentNotFoundException;

import java.util.List;

public interface StudentInterface {
	
	
    public List<Course> viewCourseCatalogue() throws DatabaseException;

    public List<RegisteredCourse> viewGrades(String StudentId) throws StudentNotFoundException;

} 
