/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * @author shubh
 *
 */
public class ProfessorUI {
	String profID;
	public ProfessorUI(String profID) {
		this.profID = profID;
	}
	public void displayUI() {
		//Display the options available for the Professor
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("========= Available Operations for Professor ========");
			System.out.println("1. View Courses");
			System.out.println("2. View Enrolled Students");
			System.out.println("3. Assign grade");
			System.out.println("4. Logout\n");
			System.out.println("Enter Option : ");
			
			int input=sc.nextInt();
			switch(input)
			{
				case 1:
					//View courses taught by the professor
					viewCourses();
					break;
				case 2:
					//View enrolled students for the course
					viewStudents();
					break;
					
				case 3:
					//Add grade for a student
					assignGrades();
					break;
				case 4:
					//Logout
					sc.close();
					System.out.println("==================== Logging Out ====================");
					return;
				default:
					System.err.println("No such operation exists, valid choices 1, 2, 3, 4");
			}
		}
	}
	private void viewCourses() {
		
	}
	private void viewStudents() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter CourseID");
		int cid = sc.nextInt();
		sc.close();
	}
	private void assignGrades() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter CourseID");
		int cid = sc.nextInt();
		sc.close();
	}
}
