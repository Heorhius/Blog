package com.kaptsiug.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kaptsiug.blog.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    @Column(name = "author_id")
    private Integer authorId;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdDate;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date lastModifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tags_articles",
            joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
//    @JsonIgnoreProperties("tags")
    private Set<TagEntity> tags;

}
