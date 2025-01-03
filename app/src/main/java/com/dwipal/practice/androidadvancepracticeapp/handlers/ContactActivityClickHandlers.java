package com.dwipal.practice.androidadvancepracticeapp.handlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.dwipal.practice.androidadvancepracticeapp.NewContactActivity;

public class ContactActivityClickHandlers {
    Context context;

    public ContactActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onFabClick(View view){
        Intent intent = new Intent(context, NewContactActivity.class);
        context.startActivity(intent);
    }
}
