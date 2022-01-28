package com.example.gestiondestocke.models;

public class Article {
    private int id;
    private String libelle;
    private double pu;
    private Category category;

    public Article(String libelle, double pu, Category category) {
        this.libelle = libelle;
        this.pu = pu;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return libelle;
    }

    public void setName(String libelle) {
        this.libelle = libelle;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = Article.this.pu;
    }

    public Article(int id, String libelle, double pu) {
        this.id = id;
        this.libelle = libelle;
        this.pu = Article.this.pu;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", pu=" + pu +
                ", cat Id=" + category.getId() +
                '}';
    }
}
