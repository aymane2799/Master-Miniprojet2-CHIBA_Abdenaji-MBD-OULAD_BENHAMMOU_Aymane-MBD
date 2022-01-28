package com.example.demo.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    List<Article> getArticles(){
        return articleService.getArticles();
    }

    @PostMapping
    void createArticle(@RequestBody Article article){
        System.out.println(article.toString());
        articleService.createArticle(article);
    }

    @GetMapping("/category/{catId}")
    List<Article> getArticleByCat(@PathVariable Long catId){
        return articleService.getArticlesByCat(catId);
    }



}
