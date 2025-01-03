package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityCounterIncrementBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.Counter;

public class CounterIncrementActivity extends AppCompatActivity {

    Counter counterModel;

    ActivityCounterIncrementBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_counter_increment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_counter_increment);


        counterModel = new ViewModelProvider(this)
                .get(Counter.class);

        binding.setCounter(counterModel);



        //counterModel.getCounter().observe(this, counter -> {
        //    binding.txtCount.setText(counter.toString());
        //});
    }
}