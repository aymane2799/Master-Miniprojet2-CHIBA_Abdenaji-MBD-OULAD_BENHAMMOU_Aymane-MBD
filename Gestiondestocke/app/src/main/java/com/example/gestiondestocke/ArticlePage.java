package com.example.gestiondestocke;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class ArticlePage extends AppCompatActivity {


    ApiInterface apiInterface;
    private static final String TAG = "MainActivity";
    EditText libelle;
    EditText pu;
    AutoCompleteTextView dropdown;
    ArrayAdapter<String> dropdownAdapter;
    ArrayList<String> categories;
    int currentCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_page);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        libelle = findViewById(R.id.lib_tf);
        pu = findViewById(R.id.pu_tf);

        dropdown = findViewById(R.id.dd_tv);

        categories = new ArrayList<String>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categories = extras.getStringArrayList("categories");
        }
        dropdownAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, categories);
        dropdown.setAdapter(dropdownAdapter);
        currentCat = categories.size();

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                currentCat = position+1;
            }
        });
    }

    public void back_meth(View view) {
        System.out.println("back");
        finish();
        Intent intent = new Intent(this, MainActivity.class);

    }

    public void valid_meth(View view){
        System.out.println("================ POST FUNCTION =================");

        if(libelle.getText().toString().isEmpty() || pu.getText().toString().isEmpty() ||
                libelle.getText().toString().equals("") || pu.getText().toString().equals("")){

            show_toast("Veuillez Inserer le nom et PU de l'article");


        }else{
            System.out.println("============================= here ========================");
            try {
                System.out.println(currentCat+"     "+categories.get(currentCat));
            }catch(Exception e){
                System.out.println(e);
            }
            Article article = new Article(libelle.getText().toString() ,
                    Double.parseDouble(pu.getText().toString()),
                    new Category(currentCat, ""));
            System.out.println(article.toString());
            Call<Article> articlePostCall = apiInterface.postArticle(article);
            articlePostCall.enqueue(new Callback<Article>() {
                @Override
                public void onResponse(Call<Article> call, Response<Article> response) {
                    Log.e(TAG, "onResponse: " +  response.body());
                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
                    show_toast("Article existe deja");
                }
            });
            show_toast("Article ajoute");
            Intent intent = new Intent(this, MainActivity.class);
            finish();
        }

    }

    public void show_toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    // cacher le clavier
    public void hideKeyBoard(){
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }



}