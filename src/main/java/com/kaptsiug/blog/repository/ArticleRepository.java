package com.kaptsiug.blog.repository;

import com.kaptsiug.blog.dto.Article;
import com.kaptsiug.blog.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    Page<ArticleEntity> findByAuthorIdAndTitle(Integer authorId, String title, Pageable pageable);

    Page<ArticleEntity> findByTitle(String title, Pageable pageable);

    Page<ArticleEntity> findByAuthorId(Integer authorId, Pageable sortedFilter);
}
