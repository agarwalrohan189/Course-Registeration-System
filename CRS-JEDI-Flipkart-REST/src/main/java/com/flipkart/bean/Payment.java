/**
 * 
 */
package com.flipkart.bean;

import java.util.Random;

import com.flipkart.constant.ModeOfPaymentConstant;

/**
 * @author Shubham
 *
 */
public class Payment {
	int paymentId;
	String studentID;
	ModeOfPaymentConstant paymentMethod;
	float amount;
	boolean status;

	/**
	 * Constructor
	 * @param studentID -> Student ID
	 * @param mode -> Mode of payment
	 * @param amount -> Amount to be paid
	 */
	public Payment(String studentID, ModeOfPaymentConstant mode, float amount) {
		this.studentID = studentID;
		this.paymentMethod = mode;
		this.amount = amount;
		Random rand = new Random();
		paymentId = rand.nextInt();
		this.status = true;
	}

	/**
	 * Getter of payment ID
	 * @return payment ID
	 */
	public int getPaymentId() {
		return paymentId;
	}

	/**
	 * Setter of payment ID
	 * @param paymentId -> new Payment ID
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Getter of student ID
	 * @return student ID
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * Setter of student ID
	 * @param studentID -> New student ID
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	/**
	 * Getter of Mode of payment
	 * @return Mode of payment
	 */
	public ModeOfPaymentConstant getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Setter of Mode of payment
	 * @param paymentMethod -> New Mode of payment
	 */
	public void setPaymentMethod(ModeOfPaymentConstant paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Getter of amount
	 * @return amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * Setter of amount
	 * @param amount -> new amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * Getter of status
	 * @return status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * Setter of status
	 * @param status -> new status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
