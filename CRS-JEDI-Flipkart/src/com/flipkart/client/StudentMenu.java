/**
 * 
 */
package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
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

/**
 * @author shubh
 *
 */
public class StudentMenu {
	
	String studentID;
	StudentInterface studentInterface = new StudentOperation();
	RegistrationInterface registrationInterface = new RegistrationOperation();

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
			System.out.println("========= Available Operations for Student ========");
			System.out.println("1. View Course Catalogue");
			System.out.println("2. View Grades");
			System.out.println("3. Register");
			System.out.println("4. Add Course");
			System.out.println("5. Drop Course");
			System.out.println("6. View registered courses");
			System.out.println("7. Make Payment");
			System.out.println("8. Show Notifications");
			System.out.println("9. Logout");

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
				System.out.println("==================== Logging Out ====================");
				return;

			default:
				System.err.println("No such operation exists, valid choices 1, 2, 3, 4, 5 ,6 ,7 ,8");
			}
		}
	}

	/**
	 * View all available courses
	 */
	private void viewCourseCatalogue() {
		try {
			List<Course> courses = studentInterface.viewCourseCatalogue();
			System.out.println("Course ID\tCourse Name\tInstructor ID\tInstructor Name\tFilled Seats");
			for (Course course : courses) {
				System.out.println(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getInstructorId() +
						"\t\t" + course.getInstructorName() + "\t\t" + course.getFilledSeats());
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();			
		}
	}

	/**
	 * View grades for the semester
	 */
	private void viewGrades() {
		try {
			List<RegisteredCourse> courses = studentInterface.viewGrades(studentID);
			System.out.println("Course ID\tCourse Name\tGrade");
			for(RegisteredCourse course:courses) {
				System.out.println(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getGrade());
			}
		}
		catch (StudentNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Register courses for the semester
	 */
	private void registerCourses() {
//		if(!)
		try {
			registrationInterface.registerCourses(studentID);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Add a course
	 */
	private void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter CourseID");
		int courseID = sc.nextInt();
		sc.nextLine();
		try {
			registrationInterface.addCourse(studentID, courseID);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
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
		System.out.println("Enter CourseID");
		int courseID = sc.nextInt();
		sc.nextLine();
		try {
			registrationInterface.dropCourse(studentID, courseID);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
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
			System.out.println("Course ID\tCourse Name\tInstructor");
			for(RegisteredCourse course:courses) {
				System.out.println(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" +course.getInstructor());
			}
		}
		catch (StudentNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Pay semester Fees
	 */
	private void payFee() {
		Scanner sc = new Scanner(System.in);
		RegistrationDaoInterface registrationDaoInterface = new RegistrationDaoOperation();
		try{
			if(!registrationDaoInterface.isRegistrationDone(studentID)) {
				System.out.println("Registration not yet complete");
			}else if(registrationDaoInterface.isPaymentDone(studentID)) {
				System.out.println("Payment already done");
			}else {
				float fee = registrationInterface.calculateFee(studentID);
				System.out.println("Pending amount is : "+fee+", Do you want to pay in full? (Y/N) : ");
				if(sc.next().equals("Y")){
					float amount = fee;
					System.out.println("==================== Payment Gateway ====================");
					System.out.println("1. Credit Card");
					System.out.println("2. Net Banking");
					System.out.println("3. Debit Card");
					System.out.println("4. Scholarship");
					System.out.println("5. Demand Draft");
					
					System.out.println("Select Mode of Payment : ");
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
						System.err.println("No such mode exists, valid choices 1, 2, 3");						
					}
				}
				
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();	
		}
		finally{
//			sc.close();
		}
	}

	private void payViaDemandDraft(String studentID2, float amount) {
		System.out.println("Check with registrar's office for offline payment");			
	}

	private void payViaScholarship(String studentID2, float amount) {
		System.out.println("Check with registrar's office for confirmation of scholarship");		
	}

	private void payViaDebitCard(String studentID, float amount) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("==================== Debit Card Details ====================");
			System.out.println("Card Number:");
			sc.next();
			System.out.println("Name:");
			sc.next();
			System.out.println("Expiry Date:");
			sc.next();
			System.out.println("CVV:");
			sc.next();
			registrationInterface.payFee(studentID, ModeOfPayment.DEBIT_CARD, amount);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}finally {
			
		}
		
	}

	private void payViaNetBanking(String studentID2, float amount) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("==================== Net Banking Details ====================");
			System.out.println("User:");
			sc.next();
			System.out.println("Password:");
			sc.next();
//			sc.close()
			registrationInterface.payFee(studentID, ModeOfPayment.NET_BANKING, amount);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void payViaCreditCard(String studentID2, float amount) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("==================== Credit Card Details ====================");
			System.out.println("Card Number:");
			sc.next();
			System.out.println("Name:");
			sc.next();
			System.out.println("Expiry Date:");
			sc.next();
			System.out.println("CVV:");
			sc.next();
			registrationInterface.payFee(studentID, ModeOfPayment.CREDIT_CARD, amount);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
