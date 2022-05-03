package com.fset21.tweet.service.impl;

import com.fset21.tweet.dto.TweetRequest;
import com.fset21.tweet.exception.TweetException;
import com.fset21.tweet.model.Reply;
import com.fset21.tweet.model.Tweet;
import com.fset21.tweet.repository.TweetRepository;
import com.fset21.tweet.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<Tweet> getAllTweets() throws TweetException {
        List<Tweet> allTweets;
        try {
            allTweets = tweetRepository.findAll();
            log.info("all tweets :{}", allTweets);
        } catch (Exception e) {
            log.error("something happened");
            throw new TweetException("gen1", "Something went wrong", e);
        }

        return allTweets;
    }

    @Override
    public void addTweet(TweetRequest tweetRequest, String username) throws TweetException {
        Tweet tweet = new Tweet(tweetRequest.getTweetId(), username, tweetRequest.getMessage(), tweetRequest.getLikeCount());
        try {
            tweetRepository.save(tweet);
        } catch (Exception e) {
            throw new TweetException("gen1", "Something went wrong", e);
        }

    }

    @Override
    public Long removeTweet(String username, String id) throws TweetException {
        Long count;
        try {
            count = tweetRepository.deleteByUsernameAndTweetId(username, id);
        } catch (Exception e) {
            throw new TweetException("gen1", "something went wrong", e);
        }
        return count;
    }

    @Override
    public Tweet editTweet(TweetRequest updatedTweet, String username, String tweetId) throws TweetException {
        Optional<Tweet> oldTweet;
        try {
            oldTweet = tweetRepository.findByUsernameAndTweetId(username, tweetId);
            if (oldTweet.isPresent()) {
                if ((!updatedTweet.getMessage().isEmpty())) {
                    log.info("update");
                    oldTweet.get().setMessage(updatedTweet.getMessage());
                    tweetRepository.save(oldTweet.get());

                } else {
                    log.error("msg cannot be empty or null");
                    throw new TweetException("e001", "message cannot be empty or null");
                }
            } else {
                log.error("tweet not found");
                throw new TweetException("e404", "tweet not found");
            }

        } catch (Exception e) {
            throw new TweetException("gen1", e.getMessage(), e.getCause());
        }
        return oldTweet.get();
    }

    @Override
    public Tweet likeTweet(String username, String tweetId) throws TweetException {
        Optional<Tweet> oldTweet;
        try {
            oldTweet = tweetRepository.findByUsernameAndTweetId(username, tweetId);
            if (oldTweet.isPresent()) {
                log.info("like");
                oldTweet.get().setLikeCount(oldTweet.get().getLikeCount() + 1);
                tweetRepository.save(oldTweet.get());

            } else {
                log.error("tweet not found");
                throw new TweetException("e404", "tweet not found");
            }

        } catch (Exception e) {
            throw new TweetException("gen1", e.getMessage(), e.getCause());
        }
        return oldTweet.get();
    }

    @Override
    public Tweet unlikeTweet(String username, String tweetId) throws TweetException {
        Optional<Tweet> oldTweet;
        try {
            oldTweet = tweetRepository.findByUsernameAndTweetId(username, tweetId);
            if (oldTweet.isPresent()) {
                if (oldTweet.get().getLikeCount() > 0) {
                    log.info("unlike");
                    oldTweet.get().setLikeCount(oldTweet.get().getLikeCount() - 1);
                    tweetRepository.save(oldTweet.get());
                }

            } else {
                log.error("tweet not found");
                throw new TweetException("e404", "tweet not found");
            }

        } catch (Exception e) {
            throw new TweetException("gen1", e.getMessage(), e.getCause());
        }
        return oldTweet.get();
    }

    @Override
    public Tweet replyTweet(TweetRequest updatedTweet, String username, String tweetId) throws TweetException {
        Optional<Tweet> oldTweet;
        try {
            oldTweet = tweetRepository.findByTweetId(tweetId);
            if (oldTweet.isPresent() && updatedTweet.getReply() != null) {
                log.info("reply");
                Reply r = new Reply();
                r.setText(updatedTweet.getReply());
                r.setAuthor(username);
                if (oldTweet.get().getReplies() != null)
                    oldTweet.get().getReplies().add(r);
                else{
                    List<Reply> replies =new ArrayList<>();
                    replies.add(r);
                    oldTweet.get().setReplies(replies);
                }
                tweetRepository.save(oldTweet.get());

            } else {
                log.error("tweet not found");
                throw new TweetException("e404", "tweet not found");
            }

        } catch (TweetException e) {
            throw e;
        } catch (Exception e) {
            throw new TweetException("gen1", e.getMessage(), e.getCause());
        }
        return oldTweet.get();
    }


}
