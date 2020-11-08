package com.company;

public class SalaryCannotBeNegativeException extends RuntimeException{
    public SalaryCannotBeNegativeException (String message){
        super(message);
    }
}
