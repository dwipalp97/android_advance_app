package com.dwipal.practice.androidadvancepracticeapp;

import static com.dwipal.practice.androidadvancepracticeapp.utils.Utils.API_KEY;
import static com.dwipal.practice.androidadvancepracticeapp.utils.Utils.BASE_URL;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.dwipal.practice.androidadvancepracticeapp.adapter.MoviePagingAdapter;
import com.dwipal.practice.androidadvancepracticeapp.adapter.MoviesLoadStateAdapter;
import com.dwipal.practice.androidadvancepracticeapp.comparator.MovieComparator;
import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityMoviesPagingBinding;
import com.dwipal.practice.androidadvancepracticeapp.utils.GridSpace;
import com.dwipal.practice.androidadvancepracticeapp.viewmodel.MoviePageViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoviesPagingActivity extends AppCompatActivity {

    private MoviePageViewModel moviePageViewModel;
    ActivityMoviesPagingBinding binding;

    MoviePagingAdapter moviePagingAdapter;
    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMoviesPagingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if(API_KEY == null || API_KEY.isEmpty() || BASE_URL == null || BASE_URL.isEmpty()){
            Toast.makeText(this, "There is no required information is available", Toast.LENGTH_SHORT).show();
        }

        moviePagingAdapter = new MoviePagingAdapter(new MovieComparator(), requestManager);

        moviePageViewModel = new ViewModelProvider(this).get(MoviePageViewModel.class);

        initiateRecycler();

        //subscribe to paging data
        moviePageViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviePagingAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initiateRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);
        binding.recyclerViewMovies.addItemDecoration(new GridSpace(2, 20, true));
        binding.recyclerViewMovies.setAdapter(moviePagingAdapter
                .withLoadStateFooter(new MoviesLoadStateAdapter(view -> {
                    moviePagingAdapter.retry();
                }
                )));

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviePagingAdapter.getItemViewType(position) == MoviePagingAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }
}