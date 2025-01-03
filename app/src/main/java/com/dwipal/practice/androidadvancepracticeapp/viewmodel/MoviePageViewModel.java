package com.dwipal.practice.androidadvancepracticeapp.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.dwipal.practice.androidadvancepracticeapp.model.Movie;
import com.dwipal.practice.androidadvancepracticeapp.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MoviePageViewModel extends ViewModel {

    public Flowable<PagingData<Movie>> moviePagingDataFlowable;
    
    public MoviePageViewModel() {
        init();
    }

    private void init() {

        MoviePagingSource pagingSource = new MoviePagingSource();

        Pager<Integer, Movie> pager = new Pager(new PagingConfig(
                20,
                20,
                true,
                20,
                20*499
        ), ()-> pagingSource);

        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope);
    }
}
