/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Shubham
 *
 */
public class RegisteredCourse {

	String courseName, instructor, studentId;
	int semesterNo, courseId;
	StudentGrade grade;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public int getSemesterNo() {
		return semesterNo;
	}
	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public StudentGrade getGrade() {
		return grade;
	}
	public void setGrade(StudentGrade grade) {
		this.grade = grade;
	}
}
