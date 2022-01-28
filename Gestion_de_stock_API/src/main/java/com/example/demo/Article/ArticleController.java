package com.example.demo.Article;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Article> createArticle(@RequestBody Article article){
        System.out.println(article.toString());
        return articleService.createArticle(article);
    }

    @GetMapping("/category/{catId}")
    List<Article> getArticleByCat(@PathVariable Long catId){
        return articleService.getArticlesByCat(catId);
    }



}
