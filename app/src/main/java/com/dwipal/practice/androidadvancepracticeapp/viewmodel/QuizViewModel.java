package com.dwipal.practice.androidadvancepracticeapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dwipal.practice.androidadvancepracticeapp.model.QuestionListResponse;
import com.dwipal.practice.androidadvancepracticeapp.repository.QuizRepository;

public class QuizViewModel extends ViewModel {

    QuizRepository quizRepository = new QuizRepository();

    LiveData<QuestionListResponse> questionListResponseLiveData;

    public QuizViewModel() {
        questionListResponseLiveData = quizRepository.getQuestions();
    }

    public LiveData<QuestionListResponse> getQuestionListResponseLiveData() {
        return questionListResponseLiveData;
    }
}
