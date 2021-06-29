package com.kaptsiug.blog.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    {
        articles = new HashSet<>();
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private Set<ArticleEntity> articles;


}
