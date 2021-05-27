/**
 * 
 */
package com.flipkart.exception;

/**
 * below class throws exception when invalid bank details is entered
 */
public class InvalidBankDetailsException extends Exception{

    /**
     * Message of exception
     * @return -> exception's message
     */
    @Override
    public String getMessage() {
        return "invalid Bank details entered";
    }
}
