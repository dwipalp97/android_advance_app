package com.dwipal.practice.androidadvancepracticeapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dwipal.practice.androidadvancepracticeapp.model.Question;
import com.dwipal.practice.androidadvancepracticeapp.model.QuestionListResponse;
import com.dwipal.practice.androidadvancepracticeapp.service.QuestionsAPI;
import com.dwipal.practice.androidadvancepracticeapp.service.QuizRetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {

    private QuestionsAPI questionsAPI;


    public QuizRepository() {
        this.questionsAPI = QuizRetrofitInstance.getRetrofitInstance().create(QuestionsAPI.class);
    }

    public LiveData<QuestionListResponse> getQuestions(){
        MutableLiveData<QuestionListResponse> mutableLiveData = new MutableLiveData<>();

        Call<QuestionListResponse> call = questionsAPI.getQuestions();

        call.enqueue(new Callback<QuestionListResponse>() {
            @Override
            public void onResponse(Call<QuestionListResponse> call, Response<QuestionListResponse> response) {
                Log.d("QuizApp", "onResponse: " + response.toString());
                QuestionListResponse questionListResponse = response.body();
                mutableLiveData.setValue(questionListResponse);
            }

            @Override
            public void onFailure(Call<QuestionListResponse> call, Throwable throwable) {
                Log.d("QuizApp", "onFailure: " + throwable.getMessage());
            }
        });

        return mutableLiveData;
    }
}
