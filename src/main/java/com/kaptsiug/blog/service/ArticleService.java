package com.kaptsiug.blog.service;

import com.kaptsiug.blog.dto.Article;
import com.kaptsiug.blog.entity.sql.ArticleEntity;
import com.kaptsiug.blog.mapper.ArticleMapper;
import com.kaptsiug.blog.repository.sql.ArticleRepository;
import com.kaptsiug.blog.repository.sql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    public List<Article> getAllWithParams(Integer skip, Integer limit, String title, Integer authorId, String fieldName, String order) {
        List<Article> articles = new ArrayList<>();
        int page = (limit == 0) ? 0 : skip / limit;

        Pageable sortedFilter =
                PageRequest.of(page, limit, order.equals("asc") ? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending());
        Page<ArticleEntity> articleEntityPage = null;
        if (!title.isEmpty() && authorId != 0) {
            articleEntityPage = articleRepository.findByAuthorIdAndTitle(authorId, title, sortedFilter);
        } else if (!title.isEmpty()) {
            articleEntityPage = articleRepository.findByTitle(title, sortedFilter);
        } else if (authorId != 0) {
            articleEntityPage = articleRepository.findByAuthorId(authorId, sortedFilter);
        } else {
            articleEntityPage = articleRepository.findAll(sortedFilter);
        }
        return articleMapper.toArticles(articleEntityPage.getContent());

    }

    public Article save(Article article) {
        if (!userRepository.existsById(article.getAuthorId())) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
        ArticleEntity articleEntity = articleMapper.toArticleEntity(article);
        return articleMapper.toArticleDto(articleRepository.save(articleEntity));
    }
}
