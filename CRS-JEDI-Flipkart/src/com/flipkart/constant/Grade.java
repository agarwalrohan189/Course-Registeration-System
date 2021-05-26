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
     * Method to convert integer to Grade enum
     * @return Grade in String
     */
    public static Grade fromInt(int val) {
    	switch (val) {
            case 10:
                return Grade.A1;
            case 9:
                return Grade.A2;
            case 8:
                return Grade.B1;
            case 7:
                return Grade.B2;
            case 6:
                return Grade.C1;
            case 5:
                return Grade.C2;
            case 4:
                return Grade.D1;
            case 3:
                return Grade.E1;
            default:
                System.err.println("No such grade Exists");
                return null;
        }
    }

    /**
     * Method to convert integer to Grade enum
     * @return Grade in String
     */
    public static Grade fromString(String val) {
    	switch (val) {
            case "A1":
                return Grade.A1;
            case "A2":
                return Grade.A2;
            case "B1":
                return Grade.B1;
            case "B2":
                return Grade.B2;
            case "C1":
                return Grade.C1;
            case "C2":
                return Grade.C2;
            case "D1":
                return Grade.D1;
            case "E1":
                return Grade.E1;
            default:
                System.err.println("No such grade Exists");
                return null;
        }
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
