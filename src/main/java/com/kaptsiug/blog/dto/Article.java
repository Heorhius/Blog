package com.kaptsiug.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotEmpty
    private Status status;
    private Date createdDate;
    private Date lastModifiedDate;
    private Set<Tag> tags;
}
