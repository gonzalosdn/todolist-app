package com.gsilva.springboot.app.gestortarea.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (){
        super("User ID NOT FOUND");
    }

}
