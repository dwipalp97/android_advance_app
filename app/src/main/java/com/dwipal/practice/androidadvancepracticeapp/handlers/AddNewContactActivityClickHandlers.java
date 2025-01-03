package com.dwipal.practice.androidadvancepracticeapp.handlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.dwipal.practice.androidadvancepracticeapp.ContactManagerActivity;
import com.dwipal.practice.androidadvancepracticeapp.model.Contact;
import com.dwipal.practice.androidadvancepracticeapp.viewmodel.ContactViewModel;

public class AddNewContactActivityClickHandlers {

    Contact contact;
    Context context;

    ContactViewModel contactViewModel;
    public AddNewContactActivityClickHandlers(Contact contact, Context context, ContactViewModel contactViewModel) {
        this.contact = contact;
        this.context = context;
        this.contactViewModel = contactViewModel;
    }

    public void onSaveNewContactDetails(View view){
        if(contact.getName().isEmpty() || contact.getMobileNumber().isEmpty() || contact.getEmail().isEmpty()){
            Toast.makeText(context, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(context, ContactManagerActivity.class);

            contactViewModel.insertContact(contact);
            context.startActivity(intent);
        }
    }
}
