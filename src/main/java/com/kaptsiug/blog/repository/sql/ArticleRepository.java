package com.kaptsiug.blog.repository.sql;

import com.kaptsiug.blog.entity.sql.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    Page<ArticleEntity> findByAuthorIdAndTitle(Integer authorId, String title, Pageable pageable);

    Page<ArticleEntity> findByTitle(String title, Pageable pageable);

    Page<ArticleEntity> findByAuthorId(Integer authorId, Pageable sortedFilter);
}
