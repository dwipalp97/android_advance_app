package com.dwipal.practice.androidadvancepracticeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dwipal.practice.androidadvancepracticeapp.database.MovieRepository;
import com.dwipal.practice.androidadvancepracticeapp.model.Movie;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private Application application;
   private MovieRepository movieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        this.movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }
}
