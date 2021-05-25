/**
 * 
 */
package com.flipkart.client;

import java.util.List;

import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

/**
 * @author shubh
 *
 */
public class ProfessorMenu {

	String professorID;
	ProfessorInterface professorInterface = new ProfessorOperation();

	public ProfessorMenu(String profID) {
		this.professorID = profID;
	}

	/**
	 * Display the menu for professor
	 */
	public void displayUI() {
		// Display the options available for the Professor
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("========= Available Operations for Professor ========");
			System.out.println("1. View Courses");
			System.out.println("2. View Enrolled Students");
			System.out.println("3. Assign grade");
			System.out.println("4. Logout\n");
			System.out.println("Enter Option : ");

			int input = sc.nextInt();
			switch (input) {
			case 1:
				// View courses taught by the professor
				viewCourses();
				break;
			case 2:
				// View enrolled students for the course
				viewStudents();
				break;

			case 3:
				// Add grade for a student
				assignGrades();
				break;
			case 4:
				// Logout
				sc.close();
				System.out.println("==================== Logging Out ====================");
				return;
			default:
				System.err.println("No such operation exists, valid choices 1, 2, 3, 4");
			}
		}
	}

	private void viewCourses() {
		List<Course> courses = professorInterface.viewCourses(professorID);
		System.out.println("Course ID\tCourse Name\tSeats");
		for (Course course : courses) {
			System.out.printf(course.getCourseId() + "\t" + course.getCourseName() + "\t" + course.getSeats());
		}
	}

	private void viewStudents() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter CourseID : ");
		int courseID = sc.nextInt();
		List<Student> students = professorInterface.viewStudent(courseID, professorID);
		System.out.println("StudentID\tStudent Name\tBranch");
		for (Student student : students) {
			System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getBranch());
		}
		sc.close();
	}

	private void assignGrades() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID");
		int courseID = sc.nextInt();
		System.out.println("Enter Student ID");
		String studentID = sc.next();
		System.out.println("Enter Grade");
		String grade = sc.next();
		professorInterface.assignGrade(professorID, studentID, courseID, grade);
		sc.close();
	}
}
