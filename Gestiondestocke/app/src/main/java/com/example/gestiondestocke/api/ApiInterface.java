package com.example.gestiondestocke.api;
import com.example.gestiondestocke.models.Article;
import com.example.gestiondestocke.models.Category;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.util.List;

public interface ApiInterface {

    @GET("/categories")
    Call<List<Category>> getCategories();
//
//
    @POST("/articles")
    Call<Article> postArticle(@Body Article article);

    @GET("/articles/category/{id}")
    Call<List<Article>> getArticlesByCategory(@Path("id") Long id);
}
