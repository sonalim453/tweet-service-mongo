package com.fset21.tweet.controller;

import com.fset21.tweet.model.Tweet;
import com.fset21.tweet.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1.0")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @PostMapping("/postTweet")
    public ResponseEntity<String>  postTweet(@RequestBody Tweet tweet){
        tweetService.addTweet(tweet);
        return new ResponseEntity<>("posted tweet -- "+tweet, HttpStatus.OK);
    }

    @GetMapping("/getAllTweets")
    public ResponseEntity<List<Tweet>>  postTweet(){
        List<Tweet> allTweets = tweetService.getAllTweets();
        return new ResponseEntity<>(allTweets, HttpStatus.OK);
    }

}
