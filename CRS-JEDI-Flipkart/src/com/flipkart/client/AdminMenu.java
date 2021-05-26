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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yash
 *
 */
public class AdminMenu {
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
			System.out.println(" 6. Add Student");
			System.out.println(" 7. Remove Student");
			System.out.println(" 8. Validate Registrations");
			System.out.println(" 9. Assign Professor to course");
			System.out.println("10. Generate report card of the student");
			System.out.println("11. Logout");
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
					addStudent();
					break;

				case 7:
					removeStudent();
					break;

				case 8:
					validateRegistrations();
					break;

				case 9:
					assignProf();
					break;

				case 10:
					generateReport();
					break;

				case 11:
					logout();looping=false;
					break;
			}
		}
	}

	private void generateReport() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			System.out.println("Enter Student ID of student whose report is to be generated");
			String studentID = scanner.nextLine();

			adminDaoInterface.generateReportCard(studentID);

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void assignProf() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			System.out.println("Enter the professor ID");
			String profID = scanner.nextLine();
			System.out.println("Enter Course ID of course to be assigned");
			int cid = scanner.nextInt();scanner.nextLine();

			adminDaoInterface.assignProf(profID,cid);

			System.out.println("Course is assigned to professor");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void validateRegistrations() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			adminDaoInterface.validateRegistration();

			System.out.println(":) :) :) registrations Validated :) :) :)");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void removeStudent() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			System.out.println("Enter Student ID");
			String studentID = scanner.nextLine();

			adminDaoInterface.removeStudent(studentID);

			System.out.println("----------Student Removed---------");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void addStudent() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			System.out.println("Enter Student ID");
			String sid = scanner.nextLine();
			System.out.println("Enter Student name");
			String name = scanner.nextLine();
			Role role = Role.Student;
			System.out.println("Enter Student Password");
			String pass = scanner.nextLine();
			System.out.println("Enter Student Gender, 1=male, 2=female, 3=other");
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
			System.out.println("Enter Address of student");
			String address = scanner.nextLine();
			System.out.println("Enter Username of student");
			String username = scanner.nextLine();
			System.out.println("Enter Student DOB in dd/mm/yyyy format");
			String dob = scanner.nextLine();
			Date doB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
			System.out.println("Enter Branch of the student");
			String branch = scanner.nextLine();
			System.out.println("Enter Student's Batch year");
			int batchYear = scanner.nextInt();scanner.nextLine();

			Student student = new Student(sid,name,role,pass,gender,address,username,doB,branch,batchYear,false);

			adminDaoInterface.addStudent(student);

			System.out.println("++++++++Student Added+++++++++");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void removeProf() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			System.out.println("Enter Professor ID");
			String profID = scanner.nextLine();

			adminDaoInterface.removeProf(profID);

			System.out.println("----------Professor Removed---------");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void addProf() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

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

			adminDaoInterface.addProf(professor);

			System.out.println("++++++++++Professor Added+++++++++++");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void removeCourse() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			System.out.println("Enter CourseID of course to be removed");
			int cid = scanner.nextInt();scanner.nextLine();

			adminDaoInterface.removeCourse(cid);

			System.out.println("----------Course Removed---------");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void addCourse() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			System.out.println("Please Enter Following details of the course");
			System.out.println("Course ID:");
			int cid = scanner.nextInt();scanner.nextLine();
			System.out.println("Course Name:");
			String cname = scanner.nextLine();
//			System.out.println("Professor ID");
//			String pid = scanner.nextLine();
//			System.out.println("Professor name");
//			String pname = UserDAOOperation.getInstance().getDetails(pid).getName();
			adminDaoInterface.addCourse(new Course(cid,cname,"","",0));

			System.out.println("+++++++++Course Added+++++++++");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void viewCourseCatalogue() {
		try {
			AdminDaoInterfaceImpl adminDaoInterface = new AdminDaoInterfaceImpl();

			 List<Course> courseList = adminDaoInterface.viewCourses();

			System.out.println("Course ID\tCourse Name\tInstructor ID\tInstructor Name\tFilled Seats");
			for (Course course : courseList) {
				System.out.printf(course.getCourseId() + "\t" + course.getCourseName() + "\t" + course.getInstructorId() +
						"\t" + course.getInstructorName() + "\t" + course.getFilledSeats());
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}


	private void logout() {
		scanner.close();
		System.out.println("---------------Logging out---------------");
	}
}
