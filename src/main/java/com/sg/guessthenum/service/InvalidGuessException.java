package com.sg.guessthenum.service;

public class InvalidGuessException extends Exception{
    public InvalidGuessException(String message){
        super(message);
    }
    public InvalidGuessException(String message, Throwable cause){
        super(message, cause);
    }
}
