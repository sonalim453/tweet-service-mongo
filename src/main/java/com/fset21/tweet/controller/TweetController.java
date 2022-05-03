package com.fset21.tweet.controller;

import com.fset21.tweet.dto.TweetRequest;
import com.fset21.tweet.exception.TweetException;
import com.fset21.tweet.model.Tweet;
import com.fset21.tweet.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1.0/tweets")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @PostMapping("/{username}/add")
    public ResponseEntity<String> postTweet(@RequestBody TweetRequest tweetRequest, @PathVariable(name = "username") String username) throws TweetException {
        tweetService.addTweet(tweetRequest, username);
        return new ResponseEntity<>("posted tweet -- " + tweetRequest, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tweet>> postTweet() throws TweetException {
        List<Tweet> allTweets = tweetService.getAllTweets();
        return new ResponseEntity<>(allTweets, HttpStatus.OK);
    }

    @DeleteMapping("/{username}/delete/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String id) throws TweetException {
        Long deleteCount = tweetService.removeTweet(username, id);
        return new ResponseEntity<>("number of tweets deleted: " + deleteCount, HttpStatus.OK);
    }

    //      PUT	/api/v1.0/tweets/<username>/update/<id>	Update tweet
    @PutMapping("/{username}/update/{id}")
    public ResponseEntity<Tweet> updateTweet(@RequestBody TweetRequest tweetRequest,@PathVariable(name = "username") String username, @PathVariable(name = "id") String id) throws TweetException {
        Tweet updatedTweet = tweetService.editTweet(tweetRequest,username, id);
        return new ResponseEntity<>(updatedTweet, HttpStatus.OK);
    }

    @PutMapping("/{username}/like/{id}")
    public ResponseEntity<Tweet> likeTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String id) throws TweetException {
        Tweet updatedTweet = tweetService.likeTweet(username, id);
        return new ResponseEntity<>(updatedTweet, HttpStatus.OK);
    }

    @PutMapping("/{username}/unlike/{id}")
    public ResponseEntity<Tweet> unlikeTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String id) throws TweetException {
        Tweet updatedTweet = tweetService.unlikeTweet(username, id);
        return new ResponseEntity<>(updatedTweet, HttpStatus.OK);
    }

    @PutMapping("/{username}/reply/{id}")
    public ResponseEntity<Tweet> replyTweet(@RequestBody TweetRequest tweetRequest,@PathVariable(name = "username") String username, @PathVariable(name = "id") String id) throws TweetException {
        Tweet updatedTweet = tweetService.replyTweet(tweetRequest,username, id);
        return new ResponseEntity<>(updatedTweet, HttpStatus.OK);
    }
}
