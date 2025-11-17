package com.spendSmart.spendSmart.helpers;

public class resourceNotFoundException extends RuntimeException{

    public resourceNotFoundException (String message){
        super(message);
    }
    
    public resourceNotFoundException (){
        super("Resource Not Found");
    }

}
