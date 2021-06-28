package com.kaptsiug.blog.dto;

import com.kaptsiug.blog.entity.sql.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    private Integer id;
    private String name;
    private Set<ArticleEntity> articles;
}
