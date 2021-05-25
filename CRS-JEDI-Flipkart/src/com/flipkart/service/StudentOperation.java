/**
 * 
 */
package com.flipkart.service;

import java.util.Date;
import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author rohanagarwal
 *
 */
public class StudentOperation implements StudentInterface {

	@Override
	public void register(String studentId, String name, String role, String password, String gender, String address, String username,
			Date doB, String branch, Course[] coursesEnrolled, int batchYear) throws StudentNotRegisteredException {
		// TODO Auto-generated method stub
		
		Student newStudent = new Student(studentId, name, role, password, gender, address, username,
		doB, branch, coursesEnrolled, batchYear);
		
		//Dao method
	}

	@Override
	public List<Course> viewCourseCatalogue() {
		// TODO Auto-generated method stub
		
		//Dao method
		return null;
	}

	@Override
	public List<StudentGrade> viewGrades(String StudentId) throws StudentNotFoundException {
		// TODO Auto-generated method stub
		
		//Dao method 
		return null;
	}

}
