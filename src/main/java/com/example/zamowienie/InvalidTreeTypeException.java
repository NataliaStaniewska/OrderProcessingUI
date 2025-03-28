package com.example.zamowienie;

public class InvalidTreeTypeException extends InvalidOrderException {
    public InvalidTreeTypeException(String message) {
        super(message);
    }
}

//nazwy klas zmienic i message nie w klasie, w sprawozdaniu pokazac ze ta klasa nadrzedna lapie wszytskie wyjtki