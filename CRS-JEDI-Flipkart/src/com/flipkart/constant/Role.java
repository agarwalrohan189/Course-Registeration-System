/**
 * 
 */
package com.flipkart.constant;

/**
 * @author Shubham
 *
 */
public enum Role {
	Student,
	Professor,
	Admin;
	
	public static int roleToInt(Role role)
	{
		switch (role)
		{
		case Student: return 0;
		case Professor: return 1;
		default: return 2;
		}
	}
}
