package com.example.gestiondestocke.models;

public class Category {
    private int id;
    private String designiation;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", designiation='" + designiation + '\'' +
                '}';
    }

    public Category(String designiation) {
        this.designiation = designiation;
    }

    public Category(int id, String designiation) {
        this.id = id;
        this.designiation = designiation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesigniation() {
        return designiation;
    }

    public void setDesigniation(String designiation) {
        this.designiation = designiation;
    }
}
