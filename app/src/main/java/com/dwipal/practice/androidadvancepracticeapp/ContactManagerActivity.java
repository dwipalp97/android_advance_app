package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwipal.practice.androidadvancepracticeapp.adapter.ContactAdapter;
import com.dwipal.practice.androidadvancepracticeapp.database.ContactDatabase;
import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityContactManagerBinding;
import com.dwipal.practice.androidadvancepracticeapp.handlers.ContactActivityClickHandlers;
import com.dwipal.practice.androidadvancepracticeapp.model.Contact;
import com.dwipal.practice.androidadvancepracticeapp.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactManagerActivity extends AppCompatActivity {
    private ContactDatabase contactDatabase;
    private ActivityContactManagerBinding binding;
    private ContactActivityClickHandlers clickHandlers;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();
    private ContactAdapter contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_manager);
        clickHandlers = new ContactActivityClickHandlers(this);
        binding.setClickHandlers(clickHandlers);

        RecyclerView recyclerView = binding.rcvContacts;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        contactDatabase = ContactDatabase.getInstance(this);

        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        Contact c1 = new Contact("Dwipal Parmar", "dwipal@gmail.com","+91 9876543210");
        contactViewModel.insertContact(c1);

        Contact c2 = new Contact("Neha Patel", "neha@gmail.com","+91 987653343210");
        contactViewModel.insertContact(c2);
        Contact c3 = new Contact("John Duo", "john@gmail.com","+91 98765435210");
        contactViewModel.insertContact(c3);
        Contact c4 = new Contact("Alexa Christ", "alexa@gmail.com","+91 463353535");
        contactViewModel.insertContact(c4);

        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactArrayList.clear();
                for (Contact contact : contacts) {
                    Log.i("ContactManagerActivity", "onChanged: " + contact.getName());
                    contactArrayList.add(contact);
                }
                contactAdapter.notifyDataSetChanged();
            }
        });


        contactAdapter = new ContactAdapter(contactArrayList);
        recyclerView.setAdapter(contactAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact = contactArrayList.get(viewHolder.getAdapterPosition());
                contactViewModel.deleteContact(contact);
            }
        }).attachToRecyclerView(recyclerView);
    }
}