package com.example.HTGOIT_2_18.model.exception;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(String message){
        super(message);
    }
}
