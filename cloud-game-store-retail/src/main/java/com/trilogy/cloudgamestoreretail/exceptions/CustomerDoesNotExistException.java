package com.trilogy.cloudgamestoreretail.exceptions;

public class CustomerDoesNotExistException extends IllegalArgumentException {
    public CustomerDoesNotExistException(String errorMessage) {super(errorMessage);   }
}
