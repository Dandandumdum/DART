package com.company;

public class RentCannotBeNegativeException extends RuntimeException {
    public RentCannotBeNegativeException(String message) {
        super(message);
    }
}
