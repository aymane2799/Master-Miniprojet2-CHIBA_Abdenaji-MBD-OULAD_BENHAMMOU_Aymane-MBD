package com.example.demo.Article;

import com.example.demo.Category.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_sequence")
    private Long id;
    private String libelle;
    private double PU;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id", referencedColumnName = "id")

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", PU=" + PU +
                ", category=" + category.getId() +
                '}';
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPU() {
        return PU;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPU(double PU) {
        this.PU = PU;
    }




}
