package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityNewContactBinding;
import com.dwipal.practice.androidadvancepracticeapp.handlers.AddNewContactActivityClickHandlers;
import com.dwipal.practice.androidadvancepracticeapp.model.Contact;
import com.dwipal.practice.androidadvancepracticeapp.viewmodel.ContactViewModel;

public class NewContactActivity extends AppCompatActivity {
    Contact contact;
    ActivityNewContactBinding binding;
    AddNewContactActivityClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contact = new Contact();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_contact);
        binding.setContact(contact);

        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        clickHandlers = new AddNewContactActivityClickHandlers(contact,this, contactViewModel);
        binding.setClickHandler(clickHandlers);

    }
}