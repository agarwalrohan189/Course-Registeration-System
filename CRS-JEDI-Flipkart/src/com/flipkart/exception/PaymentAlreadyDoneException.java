/**
 * 
 */
package com.flipkart.exception;

/**
 * @author sayan
 *
 */
public class PaymentAlreadyDoneException extends Exception{
	private String studentId;

	/**
	 * @param studentName
	 */
	public PaymentAlreadyDoneException(String studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * @return the name
	 */
	public String getId() {
		return studentId;
	}
	
	/**
     * Overrides the error message shown for exception class
     * @return
     */
	@Override
	public String getMessage() {
		return "Student with id " + studentId+" has already deposited fees"; 
	}
}
