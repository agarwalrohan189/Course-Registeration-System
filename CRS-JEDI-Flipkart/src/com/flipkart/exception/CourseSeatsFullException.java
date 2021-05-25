package com.flipkart.exception;

public class CourseSeatsFullException extends Exception {

    private int courseID;

    public CourseSeatsFullException(int courseID){
        this.courseID = courseID;
    }

    /**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return  "Seats are not available in : " + courseID;
	}

}
