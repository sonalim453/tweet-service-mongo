package com.fset21.tweet.repository;

import com.fset21.tweet.model.TweetError;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorRepository extends MongoRepository<TweetError, Integer> {

    TweetError findByErrorCode(String errorCode);
}
