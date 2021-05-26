/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * @author sayan
 *
 */
public class AdminMenu {
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

	private void v
}
