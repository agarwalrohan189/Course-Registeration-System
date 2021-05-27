/**
 * 
 */
package com.flipkart.client;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.service.NotificationOperation;
import com.flipkart.service.RegistrationInterface;
import com.flipkart.service.RegistrationOperation;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserOperation;

/**
 * @author shubh
 *
 */
public class StudentMenu {
	private static Logger logger = Logger.getLogger(StudentMenu.class);

	String studentID;
	StudentInterface studentInterface = StudentOperation.getInstance();
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();

	/**
	 * @param studentID
	 */
	public StudentMenu(String studentID) {
		this.studentID = studentID;
	}

	/**
	 * Display menu for student
	 */
	public void displayMenu() {
		// Display the options available for the Student
		Scanner sc = new Scanner(System.in);
		while (true) {
			logger.info("========= Available Operations for Student ========");
			logger.info("1. View Course Catalogue");
			logger.info("2. View Grades");
			logger.info("3. Register");
			logger.info("4. Add Course");
			logger.info("5. Drop Course");
			logger.info("6. View registered courses");
			logger.info("7. Make Payment");
			logger.info("8. Show Notifications");
			logger.info("9. Logout");

			int input = sc.nextInt();
			sc.nextLine();

			switch (input) {
			case 1:
				viewCourseCatalogue();
				break;

			case 2:
				viewGrades();
				break;

			case 3:
				registerCourses();
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
				NotificationOperation.getInstance().showNotifications(studentID);
				break;

			case 9:
				// Logout
//				sc.close();
				logger.info("==================== Logging Out ====================");
				return;

			default:
				logger.error("No such operation exists, valid choices 1, 2, 3, 4, 5 ,6 ,7 ,8");
			}
		}
	}

	/**
	 * View all available courses
	 */
	private void viewCourseCatalogue() {
		try {
			List<Course> courses = studentInterface.viewCourseCatalogue();
			logger.info("Course ID\tCourse Name\tInstructor ID\tInstructor Name\tFilled Seats");
			for (Course course : courses) {
				logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getInstructorId() +
						"\t\t" + course.getInstructorName() + "\t\t" + course.getFilledSeats());
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	/**
	 * View grades for the semester
	 */
	private void viewGrades() {
		try {
			List<RegisteredCourse> courses = studentInterface.viewGrades(studentID);
			logger.info("Course ID\tCourse Name\tGrade");
			for(RegisteredCourse course:courses) {
				logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getGrade());
			}
		}
		catch (StudentNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Register courses for the semester
	 */
	private void registerCourses() {
		Scanner sc = new Scanner(System.in);
		try {
			HashMap<Integer,Boolean> courseIDs = new HashMap<>();
			logger.info("Enter Course IDs for prefererred Course #1 : ");
			courseIDs.put(sc.nextInt(), true);
			logger.info("Enter Course IDs for prefererred Course #2 : ");
			courseIDs.put(sc.nextInt(), true);
			logger.info("Enter Course IDs for prefererred Course #3 : ");
			courseIDs.put(sc.nextInt(), true);
			logger.info("Enter Course IDs for prefererred Course #4 : ");
			courseIDs.put(sc.nextInt(), true);
			
			logger.info("Enter Course IDs for alternate Course #1 : ");
			courseIDs.put(sc.nextInt(), false);
			logger.info("Enter Course IDs for alternate Course #2 : ");
			courseIDs.put(sc.nextInt(), false);
			
			registrationInterface.registerCourses(studentID, courseIDs);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Add a course
	 */
	private void addCourse() {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter CourseID");
		int courseID = sc.nextInt();
		sc.nextLine();
		try {
			registrationInterface.addCourse(studentID, courseID);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		finally{
//			sc.close();
		}
	}

	/**
	 * Drop a course
	 */
	private void dropCourse() {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter CourseID");
		int courseID = sc.nextInt();
		sc.nextLine();
		try {
			registrationInterface.dropCourse(studentID, courseID);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();	
		}
		finally{
//			sc.close();
		}
	}

	/**
	 * View registered courses
	 */
	private void viewRegisteredCourses() {
		try {
			List<RegisteredCourse> courses = registrationInterface.viewRegisteredCourses(studentID);
			logger.info("Course ID\tCourse Name\tInstructor");
			for(RegisteredCourse course:courses) {
				logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" +course.getInstructor());
			}
		}
		catch (StudentNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Pay semester Fees
	 */
	private void payFee() {
		Scanner sc = new Scanner(System.in);
		RegistrationDaoInterface registrationDaoInterface = RegistrationDaoOperation.getInstance();
		try{
			if(!registrationDaoInterface.isRegistrationDone(studentID)) {
				logger.info("Registration not yet complete");
			}else if(registrationDaoInterface.isPaymentDone(studentID)) {
				logger.info("Payment already done");
			}else {
				float fee = registrationInterface.calculateFee(studentID);
				logger.info("Pending amount is : "+fee+", Do you want to pay in full? (Y/N) : ");
				if(sc.next().equals("Y")){
					float amount = fee;
					logger.info("==================== Payment Gateway ====================");
					logger.info("1. Credit Card");
					logger.info("2. Net Banking");
					logger.info("3. Debit Card");
					logger.info("4. Scholarship");
					logger.info("5. Demand Draft");
					
					logger.info("Select Mode of Payment : ");
					int modeChoice = sc.nextInt();
					switch(modeChoice)
					{
					case 1:
						payViaCreditCard(studentID, amount);
//						registrationInterface.payFee(studentID, ModeOfPayment.CREDIT_CARD, amount);
						break;
					case 2:
						payViaNetBanking(studentID, amount);
//						registrationInterface.payFee(studentID, ModeOfPayment.NET_BANKING, amount);
						break;
					case 3:
						payViaDebitCard(studentID, amount);
//						registrationInterface.payFee(studentID, ModeOfPayment.DEBIT_CARD, amount);
						break;
					case 4:
						payViaScholarship(studentID, amount);
//						registrationInterface.payFee(studentID, ModeOfPayment.DEBIT_CARD, amount);
						break;
					case 5:
						payViaDemandDraft(studentID, amount);
//						registrationInterface.payFee(studentID, ModeOfPayment.DEBIT_CARD, amount);
						break;
					default:
						logger.error("No such mode exists, valid choices 1, 2, 3");						
					}
				}
				
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

	private void payViaDemandDraft(String studentID2, float amount) {
		logger.info("Check with registrar's office for offline payment");			
	}

	private void payViaScholarship(String studentID2, float amount) {
		logger.info("Check with registrar's office for confirmation of scholarship");		
	}

	private void payViaDebitCard(String studentID, float amount) {
		try {
			Scanner sc = new Scanner(System.in);
			logger.info("==================== Debit Card Details ====================");
			logger.info("Card Number:");
			sc.next();
			logger.info("Name:");
			sc.next();
			logger.info("Expiry Date:");
			sc.next();
			logger.info("CVV:");
			sc.next();
			registrationInterface.payFee(studentID, ModeOfPayment.DEBIT_CARD, amount);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally {
			
		}
		
	}

	private void payViaNetBanking(String studentID2, float amount) {
		try {
			Scanner sc = new Scanner(System.in);
			logger.info("==================== Net Banking Details ====================");
			logger.info("User:");
			sc.next();
			logger.info("Password:");
			sc.next();
//			sc.close()
			registrationInterface.payFee(studentID, ModeOfPayment.NET_BANKING, amount);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void payViaCreditCard(String studentID2, float amount) {
		try {
			Scanner sc = new Scanner(System.in);
			logger.info("==================== Credit Card Details ====================");
			logger.info("Card Number:");
			sc.next();
			logger.info("Name:");
			sc.next();
			logger.info("Expiry Date:");
			sc.next();
			logger.info("CVV:");
			sc.next();
			registrationInterface.payFee(studentID, ModeOfPayment.CREDIT_CARD, amount);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
