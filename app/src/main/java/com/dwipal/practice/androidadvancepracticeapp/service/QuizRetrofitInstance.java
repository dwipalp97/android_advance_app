package com.dwipal.practice.androidadvancepracticeapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizRetrofitInstance {

    //private static String BASE_URL = "http://127.0.0.1:8000/quiz/";
    //below one is used for ewmulator
    private static String BASE_URL = "http://10.0.2.2/quiz/";

    public static Retrofit getRetrofitInstance() {
           return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    }
}
