package com.kaptsiug.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private Integer id;
    private String title;
    private String text;
    private Status status;
    private Integer authorId;
    private Instant createdDate;
    private Instant lastModifiedDate;
}
