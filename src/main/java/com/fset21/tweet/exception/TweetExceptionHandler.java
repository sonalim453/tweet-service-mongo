package com.fset21.tweet.exception;

import com.fset21.tweet.model.TweetError;
import com.fset21.tweet.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TweetExceptionHandler extends ResponseEntityExceptionHandler {


    private final ErrorRepository errorRepository;

    @Autowired
    public TweetExceptionHandler(ErrorRepository errorRepository){
        this.errorRepository=errorRepository;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, WebRequest request){
        Map<String,Object> jsonObject=new HashMap<>();
        if(ex instanceof TweetException){
            TweetException tweetException=(TweetException) ex;
            String errorCode=tweetException.getErrorCode();
            jsonObject.put("errorCode",errorCode);
            if(tweetException.getDetails()==null){
                jsonObject.put("details",tweetException.getErrorMessage());

            }
            else{
                jsonObject.put("details",tweetException.getDetails());
            }
            TweetError tweetError = errorRepository.findByErrorCode(errorCode);
            jsonObject.put("message",tweetError.getMessage());

        }

        return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
    }





}
