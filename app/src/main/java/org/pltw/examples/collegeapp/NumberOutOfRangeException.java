package org.pltw.examples.collegeapp;

/**
 * Created by wdumas on 6/12/2015.
 */
public class NumberOutOfRangeException extends RuntimeException {
    public NumberOutOfRangeException(String message){
        super(message);
    }

    public static String joinMessageAndYear(String message, int year){
        Integer yearBoxed = year;
        message = message + " " + yearBoxed.toString();
        return message;
    }
}
