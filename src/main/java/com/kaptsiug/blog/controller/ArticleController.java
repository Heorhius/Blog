package com.kaptsiug.blog.controller;

import com.kaptsiug.blog.dto.Article;
import com.kaptsiug.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/my")
    public List<Article> getAllMyArticles(Principal principal) {
        return articleService.getAllMyArticles(principal);
    }

    @GetMapping()
    public  List<Article> getAllArticles(@RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip,
                                         @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
                                         @RequestParam(name = "q", required = false, defaultValue = "") String title,
                                         @RequestParam(name = "author", required = false, defaultValue = "0") Integer author,
                                         @RequestParam(name = "sort", required = false, defaultValue = "title") String fieldName,
                                         @RequestParam(name = "order", required = false, defaultValue = "asc") String order) {
        return articleService.getAllArticlesWithParams(skip, limit, title, author,fieldName, order);
    }

    @PostMapping()
    public  Article addArticle(@Valid @RequestBody Article article, Principal principal) {
        return articleService.createArticle(article, principal);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Integer id, @Valid @RequestBody  Article article, Principal principal) {
        return articleService.updateArticle(id, article, principal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable Integer id, Principal principal) {
        articleService.deleteArticle(id, principal);
    }

}
