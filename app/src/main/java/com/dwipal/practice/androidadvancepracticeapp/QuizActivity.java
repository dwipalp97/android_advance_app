package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityQuizBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.Question;
import com.dwipal.practice.androidadvancepracticeapp.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    ActivityQuizBinding binding;
    QuizViewModel quizViewModel;
    List<Question> questionList;
    static int result =0;
    static int totalQuestions =0;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        result = 0;
        totalQuestions = 0;

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        loadQuestions();

        binding.btnNext.setOnClickListener(v -> {
            displayNextQuestion();
        });

        }

    private void displayNextQuestion() {
        int selectedOption = binding.radioGroup.getCheckedRadioButtonId();

        if(selectedOption!= -1){
            RadioButton radioButton = findViewById(selectedOption);

            if(radioButton.getText().toString().equals(questionList.get(i).getCorrectOption())){
                result++;
            }

            if(questionList.size()-i-1>0){
                i++;
                displayQuestion();
            }else{
                Toast.makeText(this, "Quiz Completed", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please select one option", Toast.LENGTH_SHORT).show();
        }
    }


    private  void loadQuestions(){
        quizViewModel.getQuestionListResponseLiveData().observe(this, questionListResponse -> {
            totalQuestions = questionListResponse.size();

            questionList = questionListResponse;
            Log.d("QuizApp","questionList"+ questionList.toString());
            displayQuestion();
        });
    }

    public void displayQuestion(){
        binding.txtQuestion.setText("Question "+(i+1)+" : "+questionList.get(i).getQuestion());
        binding.rbOption1.setText(questionList.get(i).getOption1());
        binding.rbOption2.setText(questionList.get(i).getOption2());
        binding.rbOption3.setText(questionList.get(i).getOption3());
        binding.rbOption4.setText(questionList.get(i).getOption4());
        binding.txtCorrectAns.setText("Correct Ans : "+ result);
        binding.radioGroup.clearCheck();
    }
}