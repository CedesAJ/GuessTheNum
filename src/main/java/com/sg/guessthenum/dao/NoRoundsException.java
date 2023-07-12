package com.sg.guessthenum.dao;

public class NoRoundsException extends Exception {
    public NoRoundsException(String message){
        super(message);
    }
    public NoRoundsException(String message, Throwable cause){
        super(message, cause);
    }
}
