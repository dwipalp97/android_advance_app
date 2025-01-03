package com.dwipal.practice.androidadvancepracticeapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.dwipal.practice.androidadvancepracticeapp.databinding.SingleMovieItemBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.Movie;

import kotlin.coroutines.CoroutineContext;

public class MoviePagingAdapter extends PagingDataAdapter<Movie, MoviePagingAdapter.MoviePagingViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;

    RequestManager glide;

    public MoviePagingAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MoviePagingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleMovieItemBinding binding = SingleMovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MoviePagingViewHolder holder = new MoviePagingViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePagingViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            holder.movieItemBinding.setMovie(movie);

            glide.load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath())
                    .into(holder.movieItemBinding.imgMovieCover);

          //  holder.movieItemBinding.txtMovieRating.setText(Double.toString(movie.getVoteAverage()));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
    }

    class MoviePagingViewHolder extends RecyclerView.ViewHolder {
        SingleMovieItemBinding movieItemBinding;
        public MoviePagingViewHolder(@NonNull SingleMovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }

    }
}
