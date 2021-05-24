/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * @author sayan
 *
 */
public class AdminUI {
	Scanner scanner = new Scanner(System.in);
	public void printAdminMenu() {
		boolean looping = true;
		while(looping) {
			System.out.println("_________________________________________");
			System.out.println("Admin Menu");
			System.out.println("1. View Course Catalog");
			System.out.println("2. Add Course");
			System.out.println("3. Delete Course");
			System.out.println("4. View Pending Users' Sign Up Approval");
			System.out.println("5. Approve Users' Sign Up");
			System.out.println("6. Logout");
			System.out.println("_________________________________________");
			int optionChosen = scanner.nextInt();
			
			switch(optionChosen) {
			case 1:
				viewCourseCatalogue();
				break;
				
			case 2:
				addCourse();
				break;
				
			case 3:
				deleteCourse();
				break;
				
			case 4:
				viewPending();
				break;
			
			case 5:
				approveUsers();
				break;
			
			case 6:
				looping = false;
				break;
		}
	}
}
	private void approveUsers() {
		// TODO Auto-generated method stub
//		System.out.println("called approveUsers()");
		System.out.println("__________________________");
		System.out.println("1. Approve All");
		System.out.println("2. Approve All Students");
		System.out.println("3. Approve All Professors");
		System.out.println("4. Approve specific users");
		System.out.println("__________________________");
		int optionChosen = scanner.nextInt();
		switch(optionChosen) {
		case 1:
			approveAll();
			break;
			
		case 2:
			approveAllStudents();
			break;
			
		case 3:
			approveAllProfessors();
			break;
			
		case 4:
			approveSpecficUsers();
			break;
		}
	}
		
	private void approveSpecficUsers() {
		// TODO Auto-generated method stub
		System.out.println("Enter comma separated user ids: ");
		String inputUIDs = scanner.next();
		System.out.println("Approved users " + inputUIDs);
	}
	private void approveAllProfessors() {
		// TODO Auto-generated method stub
		System.out.println("Approved All New Professors");
	}
	private void approveAllStudents() {
		// TODO Auto-generated method stub
		System.out.println("Approved All New Students");
	}
	private void approveAll() {
		// TODO Auto-generated method stub
		System.out.println("Approved All New Users");
	}
	
	
	private void viewPending() {
		// TODO Auto-generated method stub
		System.out.println("called viewPending()");
	}
	
	private void deleteCourse() {
		// TODO Auto-generated method stub
		System.out.println("called deleteCourse()");
	}
	private void addCourse() {
		// TODO Auto-generated method stub
		System.out.println("called addCourse()");
	}
	private void viewCourseCatalogue() {
		// TODO Auto-generated method stub
		System.out.println("called viewCourseCatalogue()");
	}
}