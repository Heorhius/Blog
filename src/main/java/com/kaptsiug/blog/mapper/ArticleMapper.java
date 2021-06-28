package com.kaptsiug.blog.mapper;

import com.kaptsiug.blog.dto.Article;
import com.kaptsiug.blog.entity.ArticleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    //    @Mapping(target = "id", source = "id")
    Article toArticleDto(ArticleEntity source);

    List<Article> toArticles(List<ArticleEntity> source);

    ArticleEntity toArticleEntity(Article article);
}
