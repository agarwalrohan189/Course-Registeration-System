package com.flipkart.exception;

class CourseLimitExceededException extends Exception{

    private int num;

    public CourseLimitExceededException(int num )
	{	
		this.num = num;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() 
	{
		return "Cannot register for more courses, already registered for " + num + " courses";
	}
}