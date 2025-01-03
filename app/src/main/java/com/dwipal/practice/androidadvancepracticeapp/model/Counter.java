package com.dwipal.practice.androidadvancepracticeapp.model;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Counter extends ViewModel {

    private MutableLiveData<Integer> counter = new MutableLiveData<>();

    public LiveData<Integer> getCounter() {
        return counter;
    }

    public void setCounter(MutableLiveData<Integer> counter) {
        this.counter = counter;
    }

    public void increaseCounter(View view) {
        int count = counter.getValue() !=null ? counter.getValue() : 0;

        counter.setValue(count+1);
    }
}
