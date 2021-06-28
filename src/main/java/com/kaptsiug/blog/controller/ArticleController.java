package com.kaptsiug.blog.controller;

import com.kaptsiug.blog.dto.Article;
import com.kaptsiug.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @GetMapping(value = "/articles")
    @ResponseBody
    public  List<Article> getAllArticles(@RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip,
                                         @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
                                         @RequestParam(name = "q", required = false, defaultValue = "") String title,
                                         @RequestParam(name = "author", required = false, defaultValue = "0") Integer author,
                                         @RequestParam(name = "sort", required = false, defaultValue = "title") String fieldName,
                                         @RequestParam(name = "order", required = false, defaultValue = "asc") String order) {
        return service.getAllWithParams(skip, limit, title, author,fieldName, order);
    }

    @PostMapping("/articles")
    @ResponseBody
    public  Article addArticle(@RequestBody Article article) {
        return service.save(article);
    }



}
