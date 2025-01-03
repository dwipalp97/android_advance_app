package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dwipal.practice.androidadvancepracticeapp.adapter.MovieAdapter;
import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityMovieBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.Movie;
import com.dwipal.practice.androidadvancepracticeapp.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movieArrayList;
    ActivityMovieBinding binding;
    MovieViewModel movieViewModel;
    MovieAdapter movieAdapter;
    SwipeRefreshLayout svMovies;
    RecyclerView rcvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.svMovie), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        getPopularMovies();
        
        svMovies = binding.svMovie;
        svMovies.setColorSchemeResources(R.color.black);
        svMovies.setOnRefreshListener(() -> {
            getPopularMovies();
            svMovies.setRefreshing(false);
        });
    }



    void getPopularMovies() {

        movieViewModel.getAllMovies().observe(this, movies -> {
            Log.d("Movies", movies.toString());
            movieArrayList = (ArrayList<Movie>) (movies);
            
            displayMoviesInRecyclerView();
           
            
        });
    }

    void displayMoviesInRecyclerView() {
        rcvMovies = binding.rcvMovies;
        rcvMovies.setLayoutManager(new GridLayoutManager(this,2));

        movieAdapter = new MovieAdapter(this, movieArrayList);
        rcvMovies.setItemAnimator(new DefaultItemAnimator());
        rcvMovies.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}