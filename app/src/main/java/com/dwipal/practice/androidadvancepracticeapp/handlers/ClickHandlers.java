package com.dwipal.practice.androidadvancepracticeapp.handlers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ClickHandlers {
    Context context;

    public ClickHandlers(Context context) {
        this.context = context;
    }

    public void onClick(View v) {
        Toast.makeText(context, "Button 1 clicked", Toast.LENGTH_SHORT).show();

    }
}
