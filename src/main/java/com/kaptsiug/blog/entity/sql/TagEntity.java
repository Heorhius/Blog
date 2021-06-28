package com.kaptsiug.blog.entity.sql;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("articles")
    private Set<ArticleEntity> articles;


}
