package com.dwipal.practice.androidadvancepracticeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dwipal.practice.androidadvancepracticeapp.database.ContactRepository;
import com.dwipal.practice.androidadvancepracticeapp.model.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    public LiveData<List<Contact>> allContacts;
    private ContactRepository contactRepository;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        this.contactRepository = new ContactRepository(application);
    }

    public LiveData<List<Contact>> getAllContacts(){
        allContacts =  contactRepository.getAllContacts();
        return allContacts;
    }
    public void insertContact(Contact contact){
        contactRepository.insertContact(contact);
    }

    public void deleteContact(Contact contact){
        contactRepository.deleteContact(contact);
    }
}
