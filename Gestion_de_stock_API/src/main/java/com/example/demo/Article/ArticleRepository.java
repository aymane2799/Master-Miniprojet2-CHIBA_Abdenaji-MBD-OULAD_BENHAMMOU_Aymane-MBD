package com.example.demo.Article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository <Article, Long>{

    Optional<Article> findArticleByLibelle(String libelle);

    Optional<List<Article>> findArticlesByCategoryId(Long catId);
}
