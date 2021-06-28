package com.kaptsiug.blog.entity;

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
    @Column(name = "post_id")
    private int postId;
    @Column(name = "author_id")
    private int authorId;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdDate;

}
