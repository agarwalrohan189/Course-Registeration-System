/**
 * 
 */
package com.flipkart.exception;

/**
 * to be called during login
 *
 */
public class PasswordMismatchException extends Exception{
	
	public PasswordMismatchException(String password) {
	}
	
	@Override
	public String getMessage() 
	{
		return "Entered incorrect password";
	}

}
