package com.fset21.tweet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "tweet")
public class Tweet {

    @Id
    private String tweetId ;
    private String userId;
    private String message;
    private Date postedDateTime;
    private int likeCount;
    //private String avatarLink;


}
