package com.fset21.tweet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "error")
public class TweetError {
    @Id
    private String id;
    private String errorCode;
    private String message;
}
