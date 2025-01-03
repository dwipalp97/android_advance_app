package com.dwipal.practice.androidadvancepracticeapp.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorkManager extends Worker {
    public MyWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        int maxLimit = getInputData().getInt("max_limit",100);
        for(int i=0;i<maxLimit;i++){
            Log.d("MyWorkManager","doWork: "+i);
        }

        Data data = new Data.Builder()
                .putString("msg", "Hey Dwipal Work manager has completed this task successfully").build();
        return Result.success(data);
    }
}
