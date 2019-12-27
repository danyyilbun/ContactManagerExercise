package com.example.contactmanagerexercise.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contactmanagerexercise.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactManager extends SQLiteOpenHelper {
    public ContactManager(Context context) {
        super(context, util.DATABASE_NAME, null, util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + util.TABLE_NAME + "("
                + util.KEY_ID + " INTEGER PRIMARY KEY, " +
                util.KEY_NAME + " TEXT, " +
                util.PHONE_NUMBER + " TEXT "
                +")";
        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(DROP_TABLE, new String[]{util.DATABASE_NAME});

        onCreate(db);

    }

    //add contact
    public void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(util.KEY_NAME, contact.getName() );
        values.put(util.PHONE_NUMBER, contact.getPhoneNumber());

        db.insert(util.TABLE_NAME, null, values);
        db.close();
    }
    public Contact getContact(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();


        Cursor cursor = db.query(util.TABLE_NAME, new String[]{util.KEY_ID,
                        util.KEY_NAME,util.PHONE_NUMBER},
                util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
            Contact contact = new Contact(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2));
            db.close();
            return contact;
        }
        db.close();
        return null;
    }
    public List<Contact> getAllContacts()
    {
        List <Contact> contactList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll,null);

        if(cursor.moveToFirst())
        {
            do{

                Contact contact = new Contact(Integer.parseInt(
                        cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2));

                contactList.add(contact);
            }
            while(cursor.moveToNext());

        }

        return contactList;
    }


    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(util.TABLE_NAME, util.KEY_ID +
                        "=?",
                new String[]{String.valueOf(contact.getId())} );

    }
    public int updateContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(util.KEY_NAME, contact.getName() );
        values.put(util.PHONE_NUMBER, contact.getPhoneNumber());

        return db.update(util.TABLE_NAME,  values,  util.KEY_ID +
                        "=?",
                new String[]{String.valueOf(contact.getId())} );

    }
    public int getContactCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + util.DATABASE_NAME;
        Cursor cursor = db.rawQuery( countQuery , null);
        return cursor.getCount();
    }

}