/**
 * 
 */
package com.flipkart.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.AdminDaoInterfaceImpl;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserOperation;

/**
 * @author sayan
 *
 */
public class LoginMenu {

	private static Logger logger = Logger.getLogger(LoginMenu.class);

	/**
	 * @param args
	 */
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showMainMenu();
	}

	public static void showMainMenu() {
		while (true)
		{
			logger.info("________________________");
			logger.info("Welcome");
			logger.info("1. Login");
			logger.info("2. Sign Up (for students only)");
			logger.info("3. Exit");
			logger.info("________________________");
			int optionChosen = scanner.nextInt();
			scanner.nextLine();
			switch(optionChosen) {		
			case 1:
				login();
				break;

			case 2:
				signup();
				break;

			case 3:
				exit();
				return;
			}
		}
	}

	private static void exit() {
		// TODO Auto-generated method stub
		logger.info("Exit Application");
	}

	private static void login() {
		// TODO Auto-generated method stub
		logger.info("Enter username: ");
		String uName = scanner.next();
		logger.info("Enter password: ");
		String password = scanner.next();
		loginMain(uName, password);
	}

	private static void signup() {
		try {
			StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();

			logger.info("Enter Student ID");
			String sid = scanner.nextLine();
			logger.info("Enter Student name");
			String name = scanner.nextLine();
			Role role = Role.Student;
			logger.info("Enter Student Password");
			String pass = scanner.nextLine();
			logger.info("Enter Student Gender, 1=male, 2=female, 3=other");
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
			logger.info("Enter Address of student");
			String address = scanner.nextLine();
			logger.info("Enter Username of student");
			String username = scanner.nextLine();
			logger.info("Enter Student DOB(Date of Birth) in dd/mm/yyyy format");
			String dob = scanner.nextLine();
			Date doB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
			logger.info("Enter Branch of the student");
			String branch = scanner.nextLine();
			logger.info("Enter Student's Batch year");
			int batchYear = scanner.nextInt();scanner.nextLine();

			Student student = new Student(sid,name,role,pass,gender,address,username,doB,branch,batchYear,false, false);

			studentDaoInterface.signUp(student);

			logger.info("++++++++   Sign up complete   +++++++++");
			logger.info("++++++++ Waiting for approval +++++++++");

		}catch (Exception e){
			logger.info(e.getMessage());
		}
	}

	private static void loginMain(String userId, String password) {
		// TODO Auto-generated method stub
		Role role = null;
		try {
			role = UserOperation.getInstance().login(userId, password);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		} catch (PasswordMismatchException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		finally
		{
			if (role != null)
				logger.info("Logged in successfully with userid: " + userId);
			if (role == Role.Student)
			{
				StudentInterface studentInterface = StudentOperation.getInstance();
				try {
					if (studentInterface.isApproved(userId)) {
						StudentMenu sm = new StudentMenu(userId);
						sm.displayMenu();
					} else {
						logger.info("++++++++ Waiting for approval +++++++++");
					}
				} catch (StudentNotFoundException e) {
					logger.info(e.getMessage());
				}
			}
			else if (role == Role.Professor)
			{
				ProfessorMenu pm = new ProfessorMenu(userId);
				pm.displayMenu();
			}
			else if (role == Role.Admin)
			{
				AdminMenu am = new AdminMenu();
				am.printAdminMenu();
			}
		}
	}

//	private static void registration() {
//		// TODO Auto-generated method stub
//		logger.info("Enter name: ");
//		String name = scanner.next();
//		logger.info("Enter gender: ");
//		String gender = scanner.next();
//		logger.info("Enter dob: ");
//		String dob = scanner.next();
//		logger.info("Enter role: ");
//		String role = scanner.next();
//		logger.info("Enter password: ");
//		String password = scanner.next();
//		registrationMain(name, gender, dob, role, password);
//	}
//
//	private static void registrationMain(String name, String gender, String dob, String role, String password) {
//		// TODO Auto-generated method stub
//		logger.info("Register " + name);
//	}
	
	
}
