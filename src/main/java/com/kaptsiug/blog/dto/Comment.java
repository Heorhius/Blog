package com.kaptsiug.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer id;
    private String message;
    private int postId;
    private int authorId;
    private Date createdAt;
}
