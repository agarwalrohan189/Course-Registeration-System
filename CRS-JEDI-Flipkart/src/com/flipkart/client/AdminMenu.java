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
//	private static Logger logger = Logger.getLogger(LoginMenu.class);
	
	Scanner scanner = new Scanner(System.in);
	public void printAdminMenu() {
		boolean looping = true;
		while (looping) {
			System.out.println("_________________________________________");
			System.out.println("Admin Menu");
			System.out.println(" 1. View Course Catalog");
			System.out.println(" 2. Add Course");
			System.out.println(" 3. Remove Course");
			System.out.println(" 4. Add Professor");
			System.out.println(" 5. Remove Professor");
			System.out.println(" 6. Approve Student");
			System.out.println(" 7. Remove Student");
			System.out.println(" 8. Validate Registrations");
			System.out.println(" 9. Confirm Fee Payment");
			System.out.println(" 10. Assign Professor to course");
			System.out.println(" 11. Generate report card of the student");
			System.out.println(" 12. Logout");
			System.out.println("Enter Option : ");
			System.out.println("_________________________________________");
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
		System.out.println("Enter Student Id:");
		String studentId = scanner.next();
		System.out.println("Select Mode Of Payment:");
		System.out.println("1. Scholarship");
		System.out.println("2. Demand Draft");
		System.out.println("3. Cancel");
		int choice = scanner.nextInt();
		switch(choice) {
			case 1:
				AdminOperation.getInstance().paymentDoneViaScholarship(studentId);
				break;
			case 2:
				AdminOperation.getInstance().paymentDoneViaDemandDraft(studentId);
				break;
			default:
				System.out.println("Confirmation Of Payment Cancelled");
				break;
		}
		
	}

	private void generateReport() {
		try {

			System.out.println("Enter Student ID of student whose report is to be generated");
			String studentID = scanner.nextLine();

			AdminOperation.getInstance().generateReportCard(studentID);

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void assignProf() {
		try {

			System.out.println("Enter the professor ID");
			String profID = scanner.nextLine();
			System.out.println("Enter Course ID of course to be assigned");
			int cid = scanner.nextInt();scanner.nextLine();

			AdminOperation.getInstance().assignProf(profID, cid);

			System.out.println("Course is assigned to professor");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void validateRegistrations() {
		try {

			AdminOperation.getInstance().validateRegistration();

			System.out.println(":) :) :) registrations Validated :) :) :)");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void removeStudent() {
		try {
			System.out.println("Enter Student ID");
			String studentID = scanner.nextLine();

			AdminOperation.getInstance().removeStudent(studentID);

			System.out.println("----------Student Removed---------");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void approveStudent() {
		try
		{
			System.out.println("Enter student id: ");
			String studentId = scanner.nextLine();
			AdminOperation.getInstance().approveStudent(studentId);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}

	private void removeProf() {
		try {

			System.out.println("Enter Professor ID");
			String profID = scanner.nextLine();
			
			AdminOperation.getInstance().removeProf(profID);

			System.out.println("----------Professor Removed---------");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void addProf() {
		try {
			System.out.println("Enter Professor ID");
			String pid = scanner.nextLine();
			System.out.println("Enter Professor name");
			String name = scanner.nextLine();
			Role role = Role.Professor;
			System.out.println("Enter Professor Password");
			String pass = scanner.nextLine();
			System.out.println("Enter Professor Gender, 1=male, 2=female, 3=other");
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
			System.out.println("Enter Address of Professor");
			String address = scanner.nextLine();
			System.out.println("Enter Username of Professor");
			String username = scanner.nextLine();
			System.out.println("Enter Professor DOB in dd/mm/yyyy format");
			String dob = scanner.nextLine();
			Date doB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
			System.out.println("Enter Professor department");
			String dept = scanner.nextLine();
			System.out.println("Enter Professor qualification(s)");
			String qual = scanner.nextLine();
			System.out.println("Enter Professor's Date of joining in dd/MM/yyyy format");
			String doj = scanner.nextLine();
			Date doJ = new SimpleDateFormat("dd/MM/yyyy").parse(doj);

			Professor professor = new Professor(pid,name,role,pass,gender,address,username,doB,dept,qual,doJ);

			AdminOperation.getInstance().addProf(professor);

			System.out.println("++++++++++Professor Added+++++++++++");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void removeCourse() {
		try {
			System.out.println("Enter CourseID of course to be removed");
			int cid = scanner.nextInt();scanner.nextLine();

			AdminOperation.getInstance().removeCourse(cid);

			System.out.println("----------Course Removed---------");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void addCourse() {
		try {
			System.out.println("Please Enter Following details of the course");
			System.out.println("Course ID:");
			int cid = scanner.nextInt();scanner.nextLine();
			System.out.println("Course Name:");
			String cname = scanner.nextLine();
			
			AdminOperation.getInstance().addCourse(new Course(cid,cname,"p0","p0",0));

			System.out.println("+++++++++Course Added+++++++++");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void viewCourseCatalogue() {
		try {
			 List<Course> courseList = AdminOperation.getInstance().viewCourses();

			System.out.println("Course ID\tCourse Name\tInstructor ID\tInstructor Name\tFilled Seats");
			for (Course course : courseList) {
				System.out.println(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getInstructorId() +
						"\t\t" + course.getInstructorName() + "\t\t" + course.getFilledSeats());
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}


	private void logout() {
//		scanner.close();
		System.out.println("---------------Logging out---------------");
	}
}
