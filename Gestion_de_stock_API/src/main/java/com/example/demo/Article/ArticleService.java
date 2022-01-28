package com.example.demo.Article;

import com.example.demo.Category.Category;
import com.example.demo.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Article> getArticles(){
            return articleRepository.findAll();
    }

//    public void assignACategoryToArticle(Long articleId, Long categoryId){
//        Article article = articleRepository.findById(articleId).get();
//
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(()-> new IllegalStateException("category with id "+categoryId+" does not exist"));
//
//        article.setCategory(category);
//        articleRepository.save(article);
//
//    }

    public void createArticle(Article article){
        Optional<Article> article1 = articleRepository.findArticleByLibelle(article.getLibelle());
        if(article1.isPresent()){
            throw new IllegalStateException("Article with name "+article.getLibelle()+" is already taken");
        }

        Category category = categoryRepository.findById(article.getCategory().getId())
                .orElseThrow(()-> new IllegalStateException("Category with id "+article.getCategory().getId()+" does not exist"));

        article.setCategory(category);
        articleRepository.save(article);
    }

    public List<Article> getArticlesByCat(Long catId) {
        return articleRepository.findArticlesByCategoryId(catId).get();
    }
}
