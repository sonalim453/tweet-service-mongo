package com.fset21.tweet.service;

import com.fset21.tweet.model.Tweet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TweetService {

    List<Tweet> getAllTweets();

    void addTweet(Tweet tweet);

    void removeTweet(String tweetId);

    Tweet editTweet(Tweet updatedTweet);

}
