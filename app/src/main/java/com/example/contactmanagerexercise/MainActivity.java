package com.example.contactmanagerexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactmanagerexercise.adapter.RecyclerViewAdapter;
import com.example.contactmanagerexercise.model.Contact;
import com.example.contactmanagerexercise.util.ContactManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> contactArrayList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactArrayList = new ArrayList<>();


        ContactManager d = new ContactManager(this);
        d.addContact(new Contact("Josh","1234567890"));
        d.addContact(new Contact("Josh2","1234567890"));
        d.addContact(new Contact("Josh3","1234567890"));
        d.addContact(new Contact("Josh4","1234567890"));
        d.addContact(new Contact("Josh6","1234567890"));
        d.addContact(new Contact("Josh7","1234567890"));
        d.addContact(new Contact("Josh8","1234567890"));
        d.addContact(new Contact("Josh9","1234567890"));
        d.addContact(new Contact("Josh10","1234567890"));
        d.addContact(new Contact("Josh11","1234567890"));
        d.addContact(new Contact("Josh12","1234567890"));
        d.addContact(new Contact("Josh13","1234567890"));



        contactArrayList = (ArrayList<Contact>) d.getAllContacts();
        recyclerView  = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerViewAdapter = new RecyclerViewAdapter(this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);;
    }
}
