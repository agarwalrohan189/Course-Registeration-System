/**
 * 
 */
package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.AdminDaoInterfaceImpl;
import com.flipkart.dao.UserDAOInterface;
import com.flipkart.dao.UserDAOOperation;
import com.flipkart.service.AdminOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * @author Yash
 *
 */
public class AdminMenu {
	private static Logger logger = Logger.getLogger(LoginMenu.class);
	
	Scanner scanner = new Scanner(System.in);
	public void printAdminMenu() {
		boolean looping = true;
		while (looping) {
			logger.info("_________________________________________");
			logger.info("Admin Menu");
			logger.info(" 1. View Course Catalog");
			logger.info(" 2. Add Course");
			logger.info(" 3. Remove Course");
			logger.info(" 4. Add Professor");
			logger.info(" 5. Remove Professor");
			logger.info(" 6. Approve Student");
			logger.info(" 7. Remove Student");
			logger.info(" 8. Validate Registrations");
			logger.info(" 9. Confirm Fee Payment");
			logger.info(" 10. Assign Professor to course");
			logger.info(" 11. Generate report card of the student");
			logger.info(" 12. Logout");
			logger.info("Enter Option : ");
			logger.info("_________________________________________");
			int optionChosen = scanner.nextInt();scanner.nextLine();

			switch (optionChosen) {
				case 1:
					viewCourseCatalogue();
					break;

				case 2:
					addCourse();
					break;

				case 3:
					removeCourse();
					break;

				case 4:
					addProf();
					break;

				case 5:
					removeProf();
					break;

				case 6:
					 approveStudent();
					break;

				case 7:
					removeStudent();
					break;

				case 8:
					validateRegistrations();
					break;

				case 9:
					confirmFeePayment();
					break;
					
				case 10:
					assignProf();
					break;

				case 11:
					generateReport();
					break;

				case 12:
					logout();looping=false;
					break;
			}
		}
	}

	private void confirmFeePayment() {
		logger.info("Enter Student Id:");
		String studentId = scanner.next();
		logger.info("Select Mode Of Payment:");
		logger.info("1. Scholarship");
		logger.info("2. Demand Draft");
		logger.info("3. Cancel");
		int choice = scanner.nextInt();
		switch(choice) {
			case 1:
				AdminOperation.getInstance().paymentDoneViaScholarship(studentId);
				break;
			case 2:
				AdminOperation.getInstance().paymentDoneViaDemandDraft(studentId);
				break;
			default:
				logger.info("Confirmation Of Payment Cancelled");
				break;
		}
		
	}

	private void generateReport() {
		try {

			logger.info("Enter Student ID of student whose report is to be generated");
			String studentID = scanner.nextLine();

			AdminOperation.getInstance().generateReportCard(studentID);

		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void assignProf() {
		try {

			logger.info("Enter the professor ID");
			String profID = scanner.nextLine();
			logger.info("Enter Course ID of course to be assigned");
			int cid = scanner.nextInt();scanner.nextLine();

			AdminOperation.getInstance().assignProf(profID, cid);

			logger.info("Course is assigned to professor");
		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void validateRegistrations() {
		try {

			AdminOperation.getInstance().validateRegistration();

			logger.info(":) :) :) registrations Validated :) :) :)");
		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void removeStudent() {
		try {
			logger.info("Enter Student ID");
			String studentID = scanner.nextLine();

			AdminOperation.getInstance().removeStudent(studentID);

			logger.info("----------Student Removed---------");
		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void approveStudent() {
		try
		{
			logger.info("Enter student id: ");
			String studentId = scanner.nextLine();
			AdminOperation.getInstance().approveStudent(studentId);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}

	private void removeProf() {
		try {

			logger.info("Enter Professor ID");
			String profID = scanner.nextLine();
			
			AdminOperation.getInstance().removeProf(profID);

			logger.info("----------Professor Removed---------");

		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void addProf() {
		try {
			logger.info("Enter Professor ID");
			String pid = scanner.nextLine();
			logger.info("Enter Professor name");
			String name = scanner.nextLine();
			Role role = Role.Professor;
			logger.info("Enter Professor Password");
			String pass = scanner.nextLine();
			logger.info("Enter Professor Gender, 1=male, 2=female, 3=other");
			int gende = scanner.nextInt();scanner.nextLine();
			Gender gender = Gender.OTHER;
			switch (gende){
				case 1:
					gender = Gender.MALE;break;
				case 2:
					gender = Gender.FEMALE;break;
				case 3:
					gender = Gender.OTHER;break;
			}
			logger.info("Enter Address of Professor");
			String address = scanner.nextLine();
			logger.info("Enter Username of Professor");
			String username = scanner.nextLine();
			logger.info("Enter Professor DOB in dd/mm/yyyy format");
			String dob = scanner.nextLine();
			Date doB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
			logger.info("Enter Professor department");
			String dept = scanner.nextLine();
			logger.info("Enter Professor qualification(s)");
			String qual = scanner.nextLine();
			logger.info("Enter Professor's Date of joining in dd/MM/yyyy format");
			String doj = scanner.nextLine();
			Date doJ = new SimpleDateFormat("dd/MM/yyyy").parse(doj);

			Professor professor = new Professor(pid,name,role,pass,gender,address,username,doB,dept,qual,doJ);

			AdminOperation.getInstance().addProf(professor);

			logger.info("++++++++++Professor Added+++++++++++");
		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void removeCourse() {
		try {
			logger.info("Enter CourseID of course to be removed");
			int cid = scanner.nextInt();scanner.nextLine();

			AdminOperation.getInstance().removeCourse(cid);

			logger.info("----------Course Removed---------");

		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void addCourse() {
		try {
			logger.info("Please Enter Following details of the course");
			logger.info("Course ID:");
			int cid = scanner.nextInt();scanner.nextLine();
			logger.info("Course Name:");
			String cname = scanner.nextLine();
			
			AdminOperation.getInstance().addCourse(new Course(cid,cname,"p0","p0",0));

			logger.info("+++++++++Course Added+++++++++");
		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private void viewCourseCatalogue() {
		try {
			 List<Course> courseList = AdminOperation.getInstance().viewCourses();

			logger.info("Course ID\tCourse Name\tInstructor ID\tInstructor Name\tFilled Seats");
			for (Course course : courseList) {
				logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getInstructorId() +
						"\t\t" + course.getInstructorName() + "\t\t" + course.getFilledSeats());
			}

		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}


	private void logout() {
//		scanner.close();
		logger.info("---------------Logging out---------------");
	}
}
