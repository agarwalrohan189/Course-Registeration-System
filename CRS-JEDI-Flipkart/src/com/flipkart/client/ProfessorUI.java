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
			System.out.println("========= Professor Options ========");
			System.out.println("1. View Courses");
			System.out.println("2. View Enrolled Students");
			System.out.println("3. Assign grade");
			System.out.println("4. Logout");
			System.out.println("====================================");
			System.out.println("INP>\tEnter Option : ");
			
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
					//add grade for a student
					assignGrades();
					break;
				case 4:
					//Logout
					sc.close();
					return;
				default:
					System.out.println("ERR>\tWrong Choice");
			}
		}
	}
	private void viewCourses() {
		
	}
	private void viewStudents() {
		
	}
	private void assignGrades() {
		
	}
}
