/**
 * 
 */
package com.flipkart.exception;

/**
 * to be called during password change
 *
 */
public class PasswordMatchedOldException extends Exception{
	public PasswordMatchedOldException(String password) {
	}
	
	@Override
	public String getMessage() 
	{
		return "Entered password is same as old password";
	}


}
