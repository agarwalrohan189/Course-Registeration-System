/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.constant.Role;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.UserOperation;

/**
 * @author sayan
 *
 */
public class LoginMenu {

	/**
	 * @param args
	 */
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showMainMenu();
	}

	public static void showMainMenu() {
		System.out.println("________________________");
		System.out.println("Welcome");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		System.out.println("________________________");
		int optionChosen = scanner.nextInt();
		switch(optionChosen) {		
		case 1:
			login();
			break;
			
		case 2:
			exit();
			break;
		}
	}

	private static void exit() {
		// TODO Auto-generated method stub
		System.out.println("Exit Application");
	}

	private static void login() {
		// TODO Auto-generated method stub
		System.out.println("Enter username: ");
		String uName = scanner.next();
		System.out.println("Enter password: ");
		String password = scanner.next();
		loginMain(uName, password);
	}

	private static void loginMain(String userId, String password) {
		// TODO Auto-generated method stub
		Role role = null;
		try {
			role = UserOperation.getInstance().login(userId, password);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (PasswordMismatchException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally
		{
			if (role != null)
				System.out.println("Login " + userId);
			if (role == Role.Student)
			{
				StudentMenu sm = new StudentMenu(userId);
				sm.displayMenu();
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
//		System.out.println("Enter name: ");
//		String name = scanner.next();
//		System.out.println("Enter gender: ");
//		String gender = scanner.next();
//		System.out.println("Enter dob: ");
//		String dob = scanner.next();
//		System.out.println("Enter role: ");
//		String role = scanner.next();
//		System.out.println("Enter password: ");
//		String password = scanner.next();
//		registrationMain(name, gender, dob, role, password);
//	}
//
//	private static void registrationMain(String name, String gender, String dob, String role, String password) {
//		// TODO Auto-generated method stub
//		System.out.println("Register " + name);
//	}
	
	
}
