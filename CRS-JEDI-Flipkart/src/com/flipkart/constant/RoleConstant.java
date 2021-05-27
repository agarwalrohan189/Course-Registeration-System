/**
 * 
 */
package com.flipkart.constant;

/**
 * @author Shubham
 *
 */
public enum RoleConstant {
	Student,
	Professor,
	Admin;

	/**
	 * Convert role to integer
	 * @param role -> role of user
	 * @return -> Integer corresponding to role
	 */
	public static int roleToInt(RoleConstant role)
	{
		switch (role)
		{
		case Student: return 0;
		case Professor: return 1;
		default: return 2;
		}
	}
}
