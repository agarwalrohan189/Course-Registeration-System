/**
 * 
 */
package com.flipkart.exception;

/**
 * @author rohanagarwal
 *
 */
public class DatabaseException extends Exception {

	/**
	 * @return message of exception
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Connection Error with the database. Try again.";
	}
	
	
}
