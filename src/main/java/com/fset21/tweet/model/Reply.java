package com.fset21.tweet.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
    private String text;
    private String author;
}
