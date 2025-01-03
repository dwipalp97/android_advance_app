package com.dwipal.practice.androidadvancepracticeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dwipal.practice.androidadvancepracticeapp.R;
import com.dwipal.practice.androidadvancepracticeapp.databinding.LoadStateItemBinding;

public class MoviesLoadStateAdapter extends LoadStateAdapter<MoviesLoadStateAdapter.MoviesLoadStateViewHolder> {

    private View.OnClickListener retryListener;
    public MoviesLoadStateAdapter(View.OnClickListener retryListener) {
        this.retryListener = retryListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesLoadStateViewHolder moviesLoadStateViewHolder, @NonNull LoadState loadState) {
            moviesLoadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public MoviesLoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LoadState loadState) {
        return new MoviesLoadStateViewHolder(parent,retryListener);
    }

    class MoviesLoadStateViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private TextView txtErrorMsg;
        private Button btnRetry;


        public MoviesLoadStateViewHolder(@NonNull ViewGroup itemView,@NonNull View.OnClickListener retryListener) {
            super(LayoutInflater.from(itemView.getContext()).inflate(R.layout.load_state_item,itemView));

            LoadStateItemBinding loadStateItemBinding = LoadStateItemBinding.bind(itemView);
            progressBar = loadStateItemBinding.progressBar;
            txtErrorMsg = loadStateItemBinding.txtErrorMsg;
            btnRetry = loadStateItemBinding.btnRetry;
            btnRetry.setOnClickListener(retryListener);
        }

        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error error = (LoadState.Error) loadState;
                txtErrorMsg.setText(error.getError().getLocalizedMessage());

                progressBar.setVisibility(loadState instanceof LoadState.Loading ?View.VISIBLE :  View.GONE);
                txtErrorMsg.setVisibility(loadState instanceof LoadState.Error ?View.VISIBLE :  View.GONE);
                btnRetry.setVisibility(loadState instanceof LoadState.Error ?View.VISIBLE :  View.GONE);
            }
        }
    }
}
