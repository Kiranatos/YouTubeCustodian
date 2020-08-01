package net.kiranatos.exceptions;

import java.io.IOException;

public class MoreThanOneTitleException extends IOException{

    public MoreThanOneTitleException(String message) {
        super(message);
    }   
    
}
