package com.dwipal.practice.androidadvancepracticeapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityFirebaseCloudDataStoreBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.Friend;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class FirebaseCloudDataStoreActivity extends AppCompatActivity {


    ActivityFirebaseCloudDataStoreBinding binding;

    private FirebaseFirestore   firestore = FirebaseFirestore.getInstance();;
    private DocumentReference documentReference = firestore.collection("Users").document("MGgBwRxe8RAfj7OIi9Ut");
    private CollectionReference collectionReference = firestore.collection("Users");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_firebase_cloud_data_store);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_cloud_data_store);

        binding.btnSend.setOnClickListener(view -> {
            saveData();
        });

        binding.btnRead.setOnClickListener(view -> {
            getAllDocumentsInCollection();
        });

        binding.btnUpdate.setOnClickListener(view -> {
            updateSpecificDocument();
        });
        binding.btnDelete.setOnClickListener(view -> {
            deleteAllDocument(); //delete a specific document
        });




    }

    public void saveData(){
        String name = binding.edtName.getText().toString();
        String email = binding.edtEmail.getText().toString();

        Friend friend = new Friend(name, email);
        collectionReference.add(friend).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String docId = documentReference.getId();
                Log.d("TAG", "onSuccess: " + docId);
            }
        });
    }

    public void getAllDocumentsInCollection(){
        collectionReference.get().addOnSuccessListener(queryDocumentSnapshots -> {
            String data="";
             for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){

                 Friend friend = queryDocumentSnapshot.toObject(Friend.class);
                 data += "Name: "+friend.getName() +" Email: "+friend.getEmail();
                 Log.d("TAG", "getAllDocumentsInCollection: " + friend.getName());
             }

             binding.txtData.setText(data);
        });
    }

    public void updateSpecificDocument(){
        String name = binding.edtName.getText().toString();
        String email = binding.edtEmail.getText().toString();

        Friend friend = new Friend(name, email);
        documentReference.update("name", name);
        documentReference.update("email", email);
    }

    private void deleteAllDocument(){
        documentReference.delete();
    }
}