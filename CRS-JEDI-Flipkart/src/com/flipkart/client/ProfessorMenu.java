/**
 * 
 */
package com.flipkart.client;

import java.util.List;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.Grade;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

/**
 * @author shubh
 *
 */
public class ProfessorMenu {

	private static Logger logger = Logger.getLogger(ProfessorMenu.class);

	String professorID;
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();

	/**
	 * Constructor
	 * @param profID
	 */
	public ProfessorMenu(String profID) {
		this.professorID = profID;
	}

	/**
	 * Display the menu for professor
	 */
	public void displayMenu() {
		// Display the options available for the Professor
		Scanner sc = new Scanner(System.in);

		while (true) {
			logger.info("========= Available Operations for Professor ========");
			logger.info("1. View Courses");
			logger.info("2. View Enrolled Students");
			logger.info("3. Assign grade");
			logger.info("4. Logout\n");
			logger.info("Enter Option : ");

			int input = sc.nextInt();
			sc.nextLine();
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
//				sc.close();
				logger.info("==================== Logging Out ====================");
				return;
			default:
				logger.error("No such operation exists, valid choices 1, 2, 3, 4");
			}
		}
	}

	/**
	 * View all courses alloted to professor
	 */
	private void viewCourses() {
		try{
			List<Course> courses = professorInterface.viewCourses(professorID);
			logger.info("Course ID\tCourse Name\tFilled Seats");
			for (Course course : courses) {
				logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getFilledSeats());
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();	
		}
		
	}

	/**
	 * View all students registered in a given course
	 */
	private void viewStudents() {
		Scanner sc = new Scanner(System.in);
		try{
			logger.info("Enter CourseID : ");
			int courseID = sc.nextInt();
			List<Student> students = professorInterface.viewStudent(courseID, professorID);
			logger.info("StudentID\tStudent Name\tBranch");
			for (Student student : students) {
				logger.info(student.getId() + "\t\t" + student.getName() + "\t\t" + student.getBranch());
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		finally{
//			sc.close();
		}
	}

	/**
	 * Assign Grades to a student
	 */
	private void assignGrades() {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter Course ID");
		int courseID = sc.nextInt();
		logger.info("Enter Student ID");
		String studentID = sc.next();
		logger.info("Enter Grade");
		String grade = sc.next();
		try {
			professorInterface.assignGrade(studentID, courseID, Grade.fromString(grade));
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();	
		}
		finally{
//			sc.close();
		}
	}
}
