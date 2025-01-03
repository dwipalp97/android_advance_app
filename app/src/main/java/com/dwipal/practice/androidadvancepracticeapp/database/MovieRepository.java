package com.dwipal.practice.androidadvancepracticeapp.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.dwipal.practice.androidadvancepracticeapp.R;
import com.dwipal.practice.androidadvancepracticeapp.model.Movie;
import com.dwipal.practice.androidadvancepracticeapp.model.MovieResponse;
import com.dwipal.practice.androidadvancepracticeapp.service.MovieAPIService;
import com.dwipal.practice.androidadvancepracticeapp.service.RetrofitInstance;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();

    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData(){
        MovieAPIService movieAPIService = RetrofitInstance.getMovieService();

        Call<MovieResponse> call = movieAPIService.getPopularMovies(application.getApplicationContext().getString(R.string.movie_api_key));

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();

                if(movieResponse != null && movieResponse.getResults() != null){
                    movies = (ArrayList<Movie>) movieResponse.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {

            }
        });

        return mutableLiveData;
    }
}

