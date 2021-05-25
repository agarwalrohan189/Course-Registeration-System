/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;

/**
 * @author shubh
 *
 */
public class StudentMenu {
	String studentID;
	StudentInterface studentInterface = new StudentOperation();

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
		
	}

	private void viewGrades() {

	}

	private void register() {

	}

	private void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter CourseID");
		int cid = sc.nextInt();
		sc.close();
	}

	private void dropCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter CourseID");
		int cid = sc.nextInt();
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
