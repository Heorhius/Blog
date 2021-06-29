package com.kaptsiug.blog.dto;

import com.kaptsiug.blog.entity.sql.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @NotEmpty
    private String name;
}
