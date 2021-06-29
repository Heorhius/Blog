package com.kaptsiug.blog.service;

import com.kaptsiug.blog.dto.Article;
import com.kaptsiug.blog.dto.Status;
import com.kaptsiug.blog.entity.sql.ArticleEntity;
import com.kaptsiug.blog.entity.sql.UserEntity;
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

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    public List<Article> getAllArticlesWithParams(Integer skip, Integer limit, String title, Integer authorId, String fieldName, String order) {
        int page = (limit == 0) ? 0 : skip / limit;

        Pageable sortedFilter =
                PageRequest.of(page, limit, order.equals("asc") ? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending());
        Page<ArticleEntity> articleEntityPage = null;
        if (!title.isEmpty() && authorId != 0) {
            articleEntityPage = articleRepository.findByUserIdAndTitleAndStatus(authorId, title, Status.PUBLIC, sortedFilter);
        } else if (!title.isEmpty()) {
            articleEntityPage = articleRepository.findByTitleAndStatus(title, Status.PUBLIC, sortedFilter);
        } else if (authorId != 0) {
            articleEntityPage = articleRepository.findByUserIdAndStatus(authorId, Status.PUBLIC, sortedFilter);
        } else {
            articleEntityPage = articleRepository.findAll(sortedFilter);
        }
        return articleMapper.INSTANCE.toArticlesDto(articleEntityPage.getContent());

    }

    public List<Article> getAllMyArticles(Principal principal) {
        UserEntity userEntity = validateUser(principal);

        return articleMapper.INSTANCE.toArticlesDto(articleRepository.findByUserId(userEntity.getId()));
    }

    public Article createArticle(Article article, Principal principal) {
        if (!userRepository.existsByEmail(principal.getName())) {
            throw new UsernameNotFoundException("User doesn't exists!");
        }

        ArticleEntity articleEntity = articleMapper.toArticleEntity(article);
        return articleMapper.INSTANCE.toArticleDto(articleRepository.save(articleEntity));
    }

    public Article updateArticle(Integer id, Article article, Principal principal) {
        UserEntity userEntity = validateUser(principal);
        ArticleEntity articleEntity = validateArticle(id, userEntity);
        ArticleEntity newArticle = articleMapper.INSTANCE.toArticleEntity(article);

        articleEntity.setTitle(newArticle.getTitle());
        articleEntity.setText(newArticle.getText());
        articleEntity.setTags(newArticle.getTags());
        articleEntity.setLastModifiedDate(new Date());
        articleEntity.setStatus(newArticle.getStatus());

        return articleMapper.INSTANCE.toArticleDto(articleRepository.save(articleEntity));
    }

    public void deleteArticle(Integer id, Principal principal) {
        UserEntity userEntity = validateUser(principal);
        ArticleEntity articleEntity = validateArticle(id, userEntity);
        articleRepository.deleteById(articleEntity.getId());
    }

    private ArticleEntity validateArticle(Integer id, UserEntity userEntity) {
        ArticleEntity articleEntity = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article doesn't exist"));

        if (!userEntity.getId().equals(articleEntity.getUser().getId())) {
            throw new IllegalArgumentException("No rights to change this article!");
        }
        return articleEntity;
    }

    private UserEntity validateUser(Principal principal) {
        UserEntity userEntity = userRepository.findByEmail(principal.getName());

        if (userEntity == null) {
            throw new UsernameNotFoundException("User doesn't exists!");
        }
        return userEntity;
    }
}
