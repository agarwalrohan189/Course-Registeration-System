/**
 * 
 */
package com.flipkart.service;

import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.StudentNotFoundException;

/**
 * @author rohanagarwal
 *
 */
public class StudentOperation implements StudentInterface {

	@Override
	public List<Course> viewCourseCatalogue() {
		// TODO Auto-generated method stub
		
		//Dao method
		return null;
	}

	@Override
	public List<RegisteredCourse> viewGrades(String StudentId) throws StudentNotFoundException {
		// TODO Auto-generated method stub
		
		//Dao method 
		return null;
	}

}
