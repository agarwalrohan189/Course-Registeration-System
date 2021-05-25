/**
 * 
 */
package com.flipkart.constant;

/**
 * @author rohanagarwal
 *
 */
public enum ModeOfPayment {

	CREDIT_CARD,NET_BANKING,DEBIT_CARD;
	
	/**
	 * Method to get Mode of Payment
	 * @param value
	 * @return Mode of Payment
	 */
	public static ModeOfPayment getModeofPayment(int value)
	{
		switch(value)
		{
			case 1:
				return ModeOfPayment.CREDIT_CARD;
			case 2:
				return ModeOfPayment.NET_BANKING;
			case 3:
				return ModeOfPayment.DEBIT_CARD;
			default:
				return null;
				
		}
			
	}
}
