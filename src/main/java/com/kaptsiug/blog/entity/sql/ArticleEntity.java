package com.kaptsiug.blog.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaptsiug.blog.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String text;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdDate;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date lastModifiedDate;

    {
        tags = new HashSet<>();
        comments = new ArrayList<>();
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommentEntity> comments;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tags_articles",
            joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private Set<TagEntity> tags;

}
