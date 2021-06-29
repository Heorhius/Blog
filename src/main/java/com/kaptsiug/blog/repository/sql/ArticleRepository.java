package com.kaptsiug.blog.repository.sql;

import com.kaptsiug.blog.dto.Status;
import com.kaptsiug.blog.entity.sql.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    Page<ArticleEntity> findByUserIdAndTitleAndStatus(Integer authorId, String title, Status status, Pageable pageable);

    Page<ArticleEntity> findByTitleAndStatus(String title, Status status, Pageable pageable);

    Page<ArticleEntity> findByUserIdAndStatus(Integer authorId, Status status, Pageable sortedFilter);

    List<ArticleEntity> findByUserId(Integer authorId);



}
