package com.example.zamowienie;

public class EmptyException extends InvalidOrderException {
    public EmptyException() {
        super("Wypełnij wsztskie pola!");
    }
}
