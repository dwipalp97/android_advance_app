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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwipal.practice.androidadvancepracticeapp.adapter.UserPhoneBookAdapter;
import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityPhoneBookBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PhoneBookActivity extends AppCompatActivity {

    ArrayList<User> userArrayList;
    UserPhoneBookAdapter userPhoneBookAdapter;
    RecyclerView rcvPhoneBook;
    ActivityPhoneBookBinding binding;

    //Firebase
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_book);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Log.d("TAG", "onDataChange:======= " + dataSnapshot.getValue());
                   User user = dataSnapshot.getValue(User.class);
                   userArrayList.add(user);
                }

                userPhoneBookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        userArrayList = new ArrayList<>();
        binding.rcvPhoneBook.setLayoutManager(new LinearLayoutManager(this));

        userPhoneBookAdapter = new UserPhoneBookAdapter(this, userArrayList );

        binding.rcvPhoneBook.setAdapter(userPhoneBookAdapter);

    }
}