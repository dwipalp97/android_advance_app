package com.dwipal.practice.androidadvancepracticeapp.database;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.dwipal.practice.androidadvancepracticeapp.interfaces.ContactDAO;
import com.dwipal.practice.androidadvancepracticeapp.model.Contact;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {
    //Available data source, database, cache, rest api
    private final ContactDAO contactDAO;

    ExecutorService executorService;
    Handler handler;
    public ContactRepository(Application application) {
        ContactDatabase database = ContactDatabase.getInstance(application);
        this.contactDAO = database.getContactDAO();

         executorService = Executors.newSingleThreadExecutor();

         handler = new Handler(Looper.getMainLooper());
    }

    public void insertContact(Contact contact){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insertContact(contact);
            }
        });

    }
    public LiveData<List<Contact>> getAllContacts() {
       return contactDAO.getAllContacts();
    }

    public void deleteContact(Contact contact){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.deleteContact(contact);
            }
        });
    }
}
