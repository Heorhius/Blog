package com.kaptsiug.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class Comment {
    @NotEmpty
    private String message;
    private Date createdAt;
}
