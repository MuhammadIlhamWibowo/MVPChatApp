package com.secdev.mvpchatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.secdev.mvpchatapp.data.model.Users
import com.secdev.mvpchatapp.R.layout.item_linear_contact
import kotlinx.android.synthetic.main.item_linear_contact.view.*

class ContactAdapter(
    private val contacts: MutableList<Users>,
    private val action: (Users) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private lateinit var context: Context

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(
            contact: Users,
            action: (Users) -> Unit
        ) {
            itemView.nameContact.text = contact.name
            itemView.phoneNumberContact.text = contact.phoneNumber

            itemView.setOnClickListener {
                action(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        context = parent.context

        return ContactViewHolder(
            LayoutInflater.from(context).inflate(
                item_linear_contact,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindItem(contacts[position], action)
    }
}