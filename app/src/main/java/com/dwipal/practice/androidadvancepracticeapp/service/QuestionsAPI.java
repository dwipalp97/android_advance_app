package com.dwipal.practice.androidadvancepracticeapp.service;

import com.dwipal.practice.androidadvancepracticeapp.model.MovieResponse;
import com.dwipal.practice.androidadvancepracticeapp.model.Question;
import com.dwipal.practice.androidadvancepracticeapp.model.QuestionListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionsAPI {
    @GET("quizapi.php")
    Call<QuestionListResponse> getQuestions();
}
