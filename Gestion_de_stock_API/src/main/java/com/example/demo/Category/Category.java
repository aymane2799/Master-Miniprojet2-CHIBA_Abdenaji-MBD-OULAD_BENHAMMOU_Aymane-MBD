package com.example.demo.Category;

import com.example.demo.Article.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    public Category() {
    }

    public Category(String designiation) {
        this.designiation = designiation;
    }

    public Category(Long id, String designiation) {
        this.id = id;
        this.designiation = designiation;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_sequence")
    private Long id;

    private String designiation;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Article> articles;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesigniation() {
        return designiation;
    }

    public void setDesigniation(String designiation) {
        this.designiation = designiation;
    }
}
