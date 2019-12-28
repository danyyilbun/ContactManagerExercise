package com.example.contactmanagerexercise.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.contactmanagerexercise.R;
import com.example.contactmanagerexercise.SecondActivity;
import com.example.contactmanagerexercise.model.Contact;
import com.google.gson.Gson;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public Context context;;
    public List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.phoneNumber.setText("+"+contact.getPhoneNumber());
        View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                Contact contact = contactList.get(position);
                Gson gson = new Gson();
                String json = gson.toJson(contact,Contact.class);

                Intent intent = new Intent(context, SecondActivity.class  );
                intent.putExtra("object",json);
                context.startActivity(intent);
            }
        };
             holder.crd.setOnClickListener(mOnClickListener);

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView phoneNumber;
        public CardView crd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crd= itemView.findViewById(R.id.cardView);
            name=itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);

        }

    }
}