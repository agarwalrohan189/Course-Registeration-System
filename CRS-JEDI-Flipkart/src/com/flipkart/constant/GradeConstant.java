/**
 * 
 */
package com.flipkart.constant;

/**
 * @author rohanagarwal
 *
 */
public enum GradeConstant {
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
    private GradeConstant(int value) {
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
    public static GradeConstant fromInt(int val) {
    	switch (val) {
            case 10:
                return GradeConstant.A1;
            case 9:
                return GradeConstant.A2;
            case 8:
                return GradeConstant.B1;
            case 7:
                return GradeConstant.B2;
            case 6:
                return GradeConstant.C1;
            case 5:
                return GradeConstant.C2;
            case 4:
                return GradeConstant.D1;
            case 3:
                return GradeConstant.E1;
            default:
            	return null;
        }
    }

    /**
     * Method to convert integer to Grade enum
     * @return Grade in String
     */
    public static GradeConstant fromString(String val) {
    	switch (val) {
            case "A1":
                return GradeConstant.A1;
            case "A2":
                return GradeConstant.A2;
            case "B1":
                return GradeConstant.B1;
            case "B2":
                return GradeConstant.B2;
            case "C1":
                return GradeConstant.C1;
            case "C2":
                return GradeConstant.C2;
            case "D1":
                return GradeConstant.D1;
            case "E1":
                return GradeConstant.E1;
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
