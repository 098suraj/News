package com.example.news.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.news.R;
import com.example.news.model.Article;
import com.example.news.model.ResponseModel;
import com.example.news.model.trial_Adapter;
import com.example.news.rests.ApiClient;
import com.example.news.rests.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView trial;
trial_Adapter adapter;
private static final String API_KEY="8ec4b1f38ce54e9790532afee87c9ca6";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //+--------------------------apiRelated-------------------------------+//
        apikk();
        //+-------------------------RecyclerView-----------------------------+//
         trial=findViewById(R.id.tialrecycler);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        trial.setLayoutManager(linearLayoutManager);

    }

    private void apikk() {
        final ApiInterface apiservice= ApiClient.getclient().create(ApiInterface.class);
        Call<ResponseModel>call=
                apiservice.getLatestNews("techcrunch",API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals("ok")){
                    List<Article>articleList=response.body().getArticles();
                    if(articleList.size()>0){
                       adapter=new trial_Adapter(articleList);
                       trial.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("out",t.toString());
            }
        });
    }
}