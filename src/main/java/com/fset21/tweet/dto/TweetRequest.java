package com.fset21.tweet.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TweetRequest {
    private String tweetId;
    private String userId;
    private String message;
    private int likeCount;
    private String editType;
    private String reply;
}
