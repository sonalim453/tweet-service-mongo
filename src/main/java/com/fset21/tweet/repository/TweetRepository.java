package com.fset21.tweet.repository;

import com.fset21.tweet.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, String> {

    @Query(value="{tweetId : ?1}")
    public Tweet getTweetByTweetId(String tweetId);

    @Query(value="{tweetId : ?1}")
    public List<Tweet> deleteTweetByTweetId(String tweetId);
}
