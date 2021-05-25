/**
 * 
 */
package com.flipkart.service;

import java.util.Date;
import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 * @author rohanagarwal
 *
 */
public class StudentOperation implements StudentInterface {

	@Override
	public void register(String id, String name, String role, String password, String gender, String address, String username,
			Date doB, String branch, Course[] coursesEnrolled, int batchYear) throws StudentNotRegisteredException {
		// TODO Auto-generated method stub
		
		Student newStudent = new Student(id, name, role, password, gender, address, username,
		doB, branch, coursesEnrolled, batchYear);
		
		//register the student using database throw exception
	}

	@Override
	public List<Course> viewCourseCatalogue(String userID) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<StudentGrade> viewGrades(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStudentId(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
