/**
 * 
 */
package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.service.RegistrationInterface;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;

/**
 * @author shubh
 *
 */
public class StudentMenu {
	String studentID;
	StudentInterface studentInterface = new StudentOperation();
	RegistrationInterface registrationInterface = new RegistrationOperation();

	public StudentMenu(String studentID) {
		this.studentID = studentID;
	}

	public void displayUI() {
		// Display the options available for the Student
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("========= Available Operations for Student ========");
			System.out.println("1. View Course Catalogue");
			System.out.println("2. View Grades");
			System.out.println("3. Register");
			System.out.println("4. Add Course");
			System.out.println("5. Drop Course");
			System.out.println("6. View registered courses");
			System.out.println("7. Make Payment");
			System.out.println("8. Logout");

			int input = sc.nextInt();

			switch (input) {
			case 1:
				viewCourseCatalogue();
				break;

			case 2:
				viewGrades();
				break;

			case 3:
				register();
				break;

			case 4:
				addCourse();
				break;

			case 5:
				dropCourse();
				break;

			case 6:
				viewRegisteredCourses();
				break;

			case 7:
				payFee();
				break;

			case 8:
				// Logout
				sc.close();
				System.out.println("==================== Logging Out ====================");
				return;

			default:
				System.err.println("No such operation exists, valid choices 1, 2, 3, 4, 5 ,6 ,7 ,8");
			}
		}
	}

	private void viewCourseCatalogue() {
		List<Course> courses = studentInterface.viewCourseCatalogue(studentID);
		System.out.println("Course ID\tCourse Name\tSeats");
		for (Course course : courses) {
			System.out.printf(course.getCourseId() + "\t" + course.getCourseName() + "\t" + course.getSeats());
		}
	}

	private void viewGrades() {
		List<StudentGrade> grades = studentInterface.viewGrades(studentID);
		System.out.println("Course ID\tCourse Name\tGrade");
		for(StudentGrade grade:grades) {
			System.out.println(grade.getCourseCode() + "\t" + grade.getCourseName() + "\t" + grade.getGrade());
		}
	}

	private void register() {
//		if(notregistered)
		registrationInterface.register();
	}

	private void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter CourseID");
		int courseID = sc.nextInt();
		registrationInterface.addCourse(studentID, courseID);
		sc.close();
	}

	private void dropCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter CourseID");
		int courseID = sc.nextInt();
		registrationInterface.dropCourse(studentID, courseID);
		sc.close();
	}

	private void viewRegisteredCourses() {
		
	}

	private void payFee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount");
		int amount = sc.nextInt();
		sc.close();
	}
}
