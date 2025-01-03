package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.dwipal.practice.androidadvancepracticeapp.workmanager.MyWorkManager;

public class WorkManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_work_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnWork = findViewById(R.id.btnWork);
        Data data = new Data.Builder()
                .putInt("max_limit", 6666).build();

        Constraints  constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        WorkRequest workRequest = new OneTimeWorkRequest
                .Builder(MyWorkManager.class)
                .setInputData(data)
               // .setConstraints(constraints)
                .build();

        btnWork.setOnClickListener(view -> {


            WorkManager.getInstance(this).enqueue(workRequest);
        });

        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, workInfo -> {
                    if (workInfo != null ) {
                        // Work is finished
                        Toast.makeText(this, "Work details"+workInfo.getState().name(), Toast.LENGTH_SHORT).show();
                        if (workInfo.getState().isFinished()) {
                            Toast.makeText(this, workInfo.getOutputData().getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}