package com.fset21.tweet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TweetException extends Exception{

    private String errorCode;
    private String errorMessage;
    private Object details;

    public TweetException(String errorCode){
        this.errorCode=errorCode;
    }

    public TweetException(String errorCode, String message){
        this.errorCode=errorCode;
        this.errorMessage=message;
    }


}
