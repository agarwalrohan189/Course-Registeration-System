/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.GradeConstant;

/**
 * @author Shubham
 *
 */
public class RegisteredCourse {

	String courseName, instructor, studentId;
	int semesterNo, courseId;
	GradeConstant grade;
	
	
	
	/**
	 * @param courseName
	 * @param instructor
	 * @param studentId
	 * @param semesterNo
	 * @param courseId
	 * @param grade
	 */
	public RegisteredCourse(String courseName, String instructor, String studentId, int semesterNo, int courseId,
			GradeConstant grade) {
		this.courseName = courseName;
		this.instructor = instructor;
		this.studentId = studentId;
		this.semesterNo = semesterNo;
		this.courseId = courseId;
		this.grade = grade;
	}
	
	
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the instructor
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the semesterNo
	 */
	public int getSemesterNo() {
		return semesterNo;
	}
	/**
	 * @param semesterNo the semesterNo to set
	 */
	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}
	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the grade
	 */
	public GradeConstant getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(GradeConstant grade) {
		this.grade = grade;
	}
	
}
