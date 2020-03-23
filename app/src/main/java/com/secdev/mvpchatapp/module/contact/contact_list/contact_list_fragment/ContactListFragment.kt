package com.secdev.mvpchatapp.module.contact.contact_list.contact_list_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.secdev.mvpchatapp.R
import com.secdev.mvpchatapp.adapter.ContactAdapter
import com.secdev.mvpchatapp.data.model.Users
import com.secdev.mvpchatapp.module.contact.contact_detail.ContactDetailActivity
import com.secdev.mvpchatapp.module.contact.contact_list.ContactListContract
import com.secdev.mvpchatapp.module.contact.contact_list.ContactListPresenter
import com.secdev.mvpchatapp.utils.Config
import com.secdev.mvpchatapp.utils.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_contact_list.*

class ContactListFragment : Fragment(),
    ContactListContract.ViewInterface {

    private val presenter by lazy { ContactListPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter
        onAttachView()
        presenter.doGetContacts()
    }

    override fun isSuccessGetContacts(contacts: MutableList<Users>) {
        val adapter = ContactAdapter(contacts) {
            val contactDetailIntent = Intent(context, ContactDetailActivity::class.java)
            contactDetailIntent.putExtra(Config.ARGS_CONTACT, act.gson.toJson(it))
            startActivity(contactDetailIntent)
        }

        try {
            recyclerContactList.adapter = adapter
            recyclerContactList.layoutManager = LinearLayoutManager(context)
            recyclerContactList.smoothScrollToPosition(contacts.size)
        } catch (e: IllegalStateException) {
            clearFindViewByIdCache()
        }
        adapter.notifyDataSetChanged()
    }

    override fun contactIsEmpty() {
        isEmptyContactList.visibility = View.VISIBLE
        recyclerContactList.visibility = View.GONE
    }

    override fun isError(message: String) {
        toast(message)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDettach()
    }
}
