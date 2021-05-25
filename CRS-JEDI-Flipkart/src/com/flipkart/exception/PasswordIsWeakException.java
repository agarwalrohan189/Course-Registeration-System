/**
 * 
 */
package com.flipkart.exception;

/**
 * @author sayan
 *
 */
public class PasswordIsWeakException extends Exception {
	public PasswordIsWeakException(String password) {
	}
	
	@Override
	public String getMessage() 
	{
		return "Entered password has less than 4 characters";
	}
}
