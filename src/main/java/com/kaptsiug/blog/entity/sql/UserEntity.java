package com.kaptsiug.blog.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String email;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    {
        articles = new HashSet<>();
        comments = new HashSet<>();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<CommentEntity> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<ArticleEntity> articles;

}
