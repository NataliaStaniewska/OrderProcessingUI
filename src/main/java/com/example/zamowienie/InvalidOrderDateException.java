package com.example.zamowienie;

public class InvalidOrderDateException extends InvalidOrderException {
    public InvalidOrderDateException(String message) {
        super(message);
    }
}
