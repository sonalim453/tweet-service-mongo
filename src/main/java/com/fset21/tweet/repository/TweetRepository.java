package com.fset21.tweet.repository;

import com.fset21.tweet.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, String> {

    @Query(value = "{tweetId : ?0}")
    Optional<Tweet> findByTweetId(String tweetId);

    @Query(value = "{username:?0,tweetId:?1}")
    Optional<Tweet> findByUsernameAndTweetId(String username, String tweetId);

    @Query(value = "{tweetId : ?0}")
    List<Tweet> deleteTweetByTweetId(String tweetId);


    @Query(value = "{username:?0,tweetId:?1}", delete = true)
    Long deleteByUsernameAndTweetId(String username, String id);


}
