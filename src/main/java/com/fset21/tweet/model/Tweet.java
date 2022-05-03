package com.fset21.tweet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "tweet")
public class Tweet {

    @Id
    private String id;
    private String tweetId;
    private String username;
    private String message;
    private Date postedDateTime=new Date();
    private int likeCount;
    private List<Reply> replies;
    //private String avatarLink;


    public Tweet(String tweetId, String username, String message, int likeCount) {
        this.tweetId = tweetId;
        this.username = username;
        this.message = message;
        this.likeCount = likeCount;
    }
}
