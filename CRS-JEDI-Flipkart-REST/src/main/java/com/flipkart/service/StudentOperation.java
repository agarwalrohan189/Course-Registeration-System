/**
 * 
 */
package com.flipkart.service;

import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentAlreadyExistsException;
import com.flipkart.exception.StudentNotAddedException;
import com.flipkart.exception.StudentNotFoundException;

/**
 * @author rohanagarwal
 *
 */
public class StudentOperation implements StudentInterface {

	private static volatile StudentOperation instance = null;

	private StudentOperation (){}
	public static StudentOperation getInstance() {
		if (instance == null) {
			synchronized (StudentOperation.class) {
				instance = new StudentOperation();
			}
		}
		return instance;
	}

	@Override
	public List<Course> viewCourseCatalogue() throws DatabaseException{
		StudentDaoInterface studentDaoInterface= StudentDaoOperation.getInstance();
		return studentDaoInterface.getCourseCatalogue();
	}

	@Override
	public List<RegisteredCourse> viewGrades(String studentId) throws StudentNotFoundException {
		RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
		return registrationInterface.viewRegisteredCourses(studentId);
	}

	@Override
	public void signUp(Student student) throws StudentAlreadyExistsException, StudentNotAddedException {
		try {
			StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();
			studentDaoInterface.signUp(student);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean isApproved(String studentID) throws StudentNotFoundException {
		try {
			StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();
			return studentDaoInterface.isApproved(studentID);
		}catch (Exception e){
			throw new StudentNotFoundException(studentID);
		}
	}
}
