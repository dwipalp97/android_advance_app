package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityQuardraticEquationSolverBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.Equation;

public class QuardraticEquationSolverActivity extends AppCompatActivity {

    ActivityQuardraticEquationSolverBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quardratic_equation_solver);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quardratic_equation_solver);

        Equation equation = new Equation(binding);
        binding.setEquation(equation);

    }
}