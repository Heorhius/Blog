package com.kaptsiug.blog.mapper;

import com.kaptsiug.blog.dto.Article;
import com.kaptsiug.blog.entity.sql.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article toArticleDto(ArticleEntity source);

    List<Article> toArticlesDto(List<ArticleEntity> source);

    ArticleEntity toArticleEntity(Article article);
}
