package com.kaptsiug.blog.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    //@Column(name = "post_id")
    //private int postId;
    //@Column(name = "author_id")
    //private int authorId;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;

}
