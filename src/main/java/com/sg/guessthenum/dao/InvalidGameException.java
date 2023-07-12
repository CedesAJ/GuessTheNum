package com.sg.guessthenum.dao;

public class InvalidGameException extends Exception{
    public InvalidGameException(String message){
        super(message);
    }
    public InvalidGameException(String message, Throwable cause){
        super(message, cause);
    }
}
