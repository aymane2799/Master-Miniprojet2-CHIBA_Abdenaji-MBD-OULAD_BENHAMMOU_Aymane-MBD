package com.example.gestiondestocke;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestiondestocke.api.ApiClient;
import com.example.gestiondestocke.api.ApiInterface;
import com.example.gestiondestocke.models.Article;
import com.example.gestiondestocke.models.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;
    public ArrayList<String> categories;
    public ArrayList<String> articlesList;
    ArrayAdapter<String> articlesAdapter;
    AutoCompleteTextView dropdown;
    ArrayAdapter<String> dropdownAdapter;
    ListView articlesLv;
    TextView nombre_produit_tv;
    TextView moyenne_tv;
    int nombre_produit;
    double moyenne;
    int currCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdown = findViewById(R.id.dd_tv);
        articlesLv = findViewById(R.id.articlesistView);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        moyenne_tv = findViewById(R.id.moyenne_tv);
        nombre_produit_tv = findViewById(R.id.numbrer_tv);
        categories = new ArrayList<String>();
        articlesList = new ArrayList<String>();
        moyenne = 0;
        nombre_produit = 0;

        dropdownAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, categories);
        getCategories();

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                // get article by category
                currCat = position+1;
                getArticles((long) currCat);
            }
        });
        if (currCat > 0)
            getArticles((long) currCat);

        articlesAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,articlesList);
    }

    @Override
    protected void onStart() {
        System.out.println("================ ON START ======================");
        super.onStart();
        moyenne = 0;
        nombre_produit = 0;
        if (currCat > 0)
            getArticles((long) currCat);
    }

    public void getArticles(Long id){
        articlesList.clear();
        articlesLv.setAdapter(articlesAdapter);

        Call<List<Article>> call = apiInterface.getArticlesByCategory(id);
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                Log.e(TAG, "onResponse: " +  response.body());
                try {

                    System.out.println("Articles ===== "+currCat);
                    moyenne = 0;
                    nombre_produit = 0;

                    response.body().forEach((article)-> {
                        articlesList.add(article.getId()+"               "+article.getName()+"              "+article.getPu());
                        moyenne += article.getPu();
                        System.out.println(moyenne + " + "+article.getPu());
                    });

                    nombre_produit = response.body().size();
                    if (nombre_produit > 0){
                        moyenne_tv.setText(moyenne / (double) nombre_produit+" Dh");
                    }else{
                        moyenne_tv.setText(0+" Dh");
                    }
                    nombre_produit_tv.setText(nombre_produit+"");

                    articlesLv.setAdapter(articlesAdapter);

                }catch(Exception e){
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
    }

    public void addBtnClick(View view){
        Intent i = new Intent(MainActivity.this, ArticlePage.class);
        i.putExtra("categories",categories);
        startActivity(i);
    }

    public void openArticlePage(){
        Intent intent = new Intent(this, ArticlePage.class);
    }

    public void getCategories() {
        Call<List<Category>> call = apiInterface.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.e(TAG, "onResponse: " +  response.body());
                categories.clear();
                response.body().forEach((category)-> {
                    categories.add(category.getDesigniation());
                });
                dropdown.setAdapter(dropdownAdapter);
                getArticles(new Long(categories.size()));
                currCat = categories.size();
                System.out.println("Categories====="+currCat);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });

    }


}