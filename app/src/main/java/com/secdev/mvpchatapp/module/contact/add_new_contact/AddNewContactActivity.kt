package com.secdev.mvpchatapp.module.contact.add_new_contact

import android.os.Bundle
import com.secdev.mvpchatapp.R
import com.secdev.mvpchatapp.utils.AppCompatActivity
import com.secdev.mvpchatapp.utils.startHomeActivity
import kotlinx.android.synthetic.main.activity_add_new_contact.*
import kotlinx.android.synthetic.main.fragment_contact_list.*

class AddNewContactActivity : AppCompatActivity(), AddNewContactContract.ViewPresenter {

    private val presenter by lazy { AddNewContactPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_contact)

        presenter
        onAttachView()
        btnAddNewContact.setOnClickListener {
            presenter.addContactToDatabase(textPhoneAddNewContact.toString())
        }
        btnBackAddNewContact.setOnClickListener {
            onBackPressed()
        }
    }

    override fun isSuccessAddContact(message: String) {
        toast(message)
        startHomeActivity()
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
