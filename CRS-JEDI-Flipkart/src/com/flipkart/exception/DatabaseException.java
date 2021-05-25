/**
 * 
 */
package com.flipkart.exception;

/**
 * @author rohanagarwal
 *
 */
public class DatabaseException extends Exception {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Connection Error with the database. Try again.";
	}
	
	
}
