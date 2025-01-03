package com.dwipal.practice.androidadvancepracticeapp.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.dwipal.practice.androidadvancepracticeapp.model.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void insertContact(Contact contact);

    @Delete
    void  deleteContact(Contact contact);

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();
}
