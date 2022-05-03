package com.fset21.tweet.service;

import com.fset21.tweet.dto.TweetRequest;
import com.fset21.tweet.exception.TweetException;
import com.fset21.tweet.model.Tweet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TweetService {

    List<Tweet> getAllTweets() throws TweetException;

    void addTweet(TweetRequest tweetRequest, String username) throws TweetException;

    Long removeTweet(String username,String id) throws TweetException;

    Tweet editTweet(TweetRequest updatedTweet,String username,String tweetId) throws TweetException;

    Tweet likeTweet(String username,String tweetId) throws TweetException;

    Tweet unlikeTweet(String username,String tweetId) throws TweetException;

    Tweet replyTweet(TweetRequest updatedTweet,String username,String tweetId) throws TweetException;



}
