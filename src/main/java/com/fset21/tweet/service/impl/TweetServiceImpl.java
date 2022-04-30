package com.fset21.tweet.service.impl;

import com.fset21.tweet.model.Tweet;
import com.fset21.tweet.repository.TweetRepository;
import com.fset21.tweet.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<Tweet> getAllTweets() {
       return tweetRepository.findAll();
    }

    @Override
    public void addTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public void removeTweet(String tweetId) {
      //  tweetRepository.delete();
    }

    @Override
    public Tweet editTweet(Tweet updatedTweet) {
        Optional<Tweet> oldTweet=tweetRepository.findById(updatedTweet.getTweetId());
        if(!oldTweet.isPresent()){
            if(!updatedTweet.toString().equals(oldTweet.toString())){
                return tweetRepository.save(updatedTweet);
            }
        }
        else
            return oldTweet.get();

        return null;
    }
}
