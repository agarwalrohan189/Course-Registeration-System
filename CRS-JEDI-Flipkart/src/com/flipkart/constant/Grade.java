/**
 * 
 */
package com.flipkart.constant;

/**
 * @author rohanagarwal
 *
 */
public enum Grade {
	A1(10),
    A2(9),
    B1(8),
    B2(7),
    C1(6),
	C2(5),
    D1(4),
    E1(3);

    final private int value;

    /**
     * Parameterized Constructor
     * @param value
     */
    private Grade(int value) {
        this.value = value;
    }

    /**
     * Method to get Grade Value
     * @return Grade Value
     */
    public int hasValue() {
        return this.value;
    }
    
    

    /**
     * Method to convert Grade enum to String
     * @return Grade in String
     */
    @Override
    public String toString() {
    	
        final String name = name();
        
        if (name.contains("1")) 
            return name.charAt(0) + "1"; 
        else if (name.contains("2")) 
            return name.charAt(0) + "2"; 
        else 
            return name;
    }
}
