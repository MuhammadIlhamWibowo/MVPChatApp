package com.secdev.mvpchatapp.module.contact.contact_list.add_new_chat

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.secdev.mvpchatapp.R
import com.secdev.mvpchatapp.adapter.ContactAdapter
import com.secdev.mvpchatapp.data.model.Users
import com.secdev.mvpchatapp.module.chat.message.MessageActivity
import com.secdev.mvpchatapp.module.contact.contact_list.ContactListContract
import com.secdev.mvpchatapp.module.contact.contact_list.ContactListPresenter
import com.secdev.mvpchatapp.utils.AppCompatActivity
import com.secdev.mvpchatapp.utils.Config
import kotlinx.android.synthetic.main.activity_add_new_chat.*

class AddNewChatActivity : AppCompatActivity(), ContactListContract.ViewInterface {

    private val presenter by lazy { ContactListPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_chat)

        presenter
        onAttachView()
        presenter.doGetContacts()
    }

    override fun isSuccessGetContacts(contacts: MutableList<Users>) {
        val adapter = ContactAdapter(contacts) {
            val addNewChatIntent = Intent(this, MessageActivity::class.java)
            addNewChatIntent.putExtra(Config.ARGS_CONTACT, gson.toJson(it))
            overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in)
            startActivity(addNewChatIntent)
        }

        adapter.notifyDataSetChanged()
        recyclerAddNewChat.adapter = adapter
        recyclerAddNewChat.layoutManager = LinearLayoutManager(this)
    }

    override fun contactIsEmpty() {
        recyclerAddNewChat.visibility = View.GONE
        isEmptyAddNewChat.visibility = View.VISIBLE
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
