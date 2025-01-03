package com.dwipal.practice.androidadvancepracticeapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dwipal.practice.androidadvancepracticeapp.interfaces.ContactDAO;
import com.dwipal.practice.androidadvancepracticeapp.model.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();

    public static final String DATABASE_NAME = "contacts_db";

    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    DATABASE_NAME).fallbackToDestructiveMigration().build();
        }

        return dbInstance;
    }
}
