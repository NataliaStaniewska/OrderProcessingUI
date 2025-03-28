package com.example.zamowienie;

public class InvalidOrderQuantityException extends InvalidOrderException {
    public InvalidOrderQuantityException(String message) {
        super(message);
    }
}
