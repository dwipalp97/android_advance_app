package com.dwipal.practice.androidadvancepracticeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dwipal.practice.androidadvancepracticeapp.databinding.ItemCardBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.User;

import java.util.ArrayList;

public class UserPhoneBookAdapter extends RecyclerView.Adapter<UserPhoneBookAdapter.UserPhoneBookViewHolder> {

    private ArrayList<User> userArrayList;
    private Context context;

    public UserPhoneBookAdapter( Context context, ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserPhoneBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding itemCardBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserPhoneBookViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPhoneBookViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.itemCardBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class UserPhoneBookViewHolder extends RecyclerView.ViewHolder {

        private ItemCardBinding itemCardBinding;

        public UserPhoneBookViewHolder(ItemCardBinding itemCardBinding) {
            super(itemCardBinding.getRoot());
            this.itemCardBinding = itemCardBinding;

            itemCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                }
            });
        }
    }
}
