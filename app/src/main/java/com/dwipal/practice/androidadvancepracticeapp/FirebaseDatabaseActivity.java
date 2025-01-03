package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dwipal.practice.androidadvancepracticeapp.model.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class FirebaseDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_firebase_database);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtMessage = findViewById(R.id.txtMessage);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Congratulations, Hello, Dwipal we have all ready to setup this firebase database to you app !!");
//
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            int i =1;
//            @Override
//            public void run() {
//                // Call your function here
//                if(i<5){
//                    myRef.setValue("New Value Added with count "+i);
//                    i++;
//                }else{
//                    timer.cancel();
//                }
//            }
//        };
//
//        // Schedule the task to run every 5 seconds (5000 milliseconds)
//        timer.schedule(task, 0, 5000);

        DatabaseReference myRef = database.getReference("Users");

        Person person = new Person("Dwipal", "testuser12@gmail.com");
        myRef.child("user1").setValue(person);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               //String message = snapshot.getValue(String.class);

               //String txtValue = txtMessage.getText().toString();
               //txtMessage.setText(txtValue+" \n"+message);

                Person person1 = snapshot.child("user1").getValue(Person.class);
                txtMessage.setText(person1.getName()+" "+person1.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}