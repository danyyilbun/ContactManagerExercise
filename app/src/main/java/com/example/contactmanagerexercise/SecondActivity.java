package com.example.contactmanagerexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.contactmanagerexercise.model.Contact;
import com.google.gson.Gson;

    public class SecondActivity extends AppCompatActivity {
        public TextView name;
        public TextView phoneNumber;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);

            phoneNumber = findViewById(R.id.phone);
            name = findViewById(R.id.name);
            try {
                Contact contact;
                Bundle bundle = getIntent().getExtras();
                Gson gson = new Gson();
                contact = gson.fromJson(bundle.getString("object"), Contact.class);
                name.setText(contact.getName());
                phoneNumber.setText(contact.getPhoneNumber());
            }
            catch(Exception e)
            {e.printStackTrace();}
        }
    }
