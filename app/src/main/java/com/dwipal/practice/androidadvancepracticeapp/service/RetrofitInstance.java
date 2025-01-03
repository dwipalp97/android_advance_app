package com.dwipal.practice.androidadvancepracticeapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit  retrofit;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieAPIService getMovieService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(MovieAPIService.class);
    }
}
