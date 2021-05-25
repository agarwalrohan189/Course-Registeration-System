/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

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
		System.out.println("1. Registration");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		System.out.println("________________________");
		int optionChosen = scanner.nextInt();
		switch(optionChosen) {
		case 1:
			registration();
			break;
			
		case 2:
			login();
			break;
			
		case 3:
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

	private static void loginMain(String uName, String password) {
		// TODO Auto-generated method stub
		try {
			UserOperation.getInstance().login(uName, password);
			System.out.println("Login " + uName);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (PasswordMismatchException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private static void registration() {
		// TODO Auto-generated method stub
		System.out.println("Enter name: ");
		String name = scanner.next();
		System.out.println("Enter gender: ");
		String gender = scanner.next();
		System.out.println("Enter dob: ");
		String dob = scanner.next();
		System.out.println("Enter role: ");
		String role = scanner.next();
		System.out.println("Enter password: ");
		String password = scanner.next();
		registrationMain(name, gender, dob, role, password);
	}

	private static void registrationMain(String name, String gender, String dob, String role, String password) {
		// TODO Auto-generated method stub
		System.out.println("Register " + name);
	}
	
	
}
