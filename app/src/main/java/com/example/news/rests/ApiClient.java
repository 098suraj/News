package com.example.news.rests;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    public  static  final String Base_url= "https://newsapi.org/v2/";
    private  static  Retrofit retrofit=null;
    public static Retrofit getclient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
