package com.company;

public class NameCannotBeEmptyException extends RuntimeException{
    public NameCannotBeEmptyException(String message) {
        super(message);
    }
}
