package com.dwipal.practice.androidadvancepracticeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dwipal.practice.androidadvancepracticeapp.databinding.ContactItemBinding;
import com.dwipal.practice.androidadvancepracticeapp.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactItemBinding contactItemBinding = ContactItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ContactViewHolder(contactItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.contactItemBinding.setContact(contact);

    }

    @Override
    public int getItemCount() {
        return contactList !=null ? contactList.size() : 0;
    }

    public void setContactList(List<Contact> contactList){
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        ContactItemBinding contactItemBinding;

        public ContactViewHolder(@NonNull ContactItemBinding contactItemBinding) {
            super(contactItemBinding.getRoot());
            this.contactItemBinding = contactItemBinding;
        }
    }
}
