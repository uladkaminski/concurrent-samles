package com.epam.jmp.completable;


public class CarWasDisqualifiedException extends RuntimeException {
    public CarWasDisqualifiedException(String message) {
        super(message);
    }
}
