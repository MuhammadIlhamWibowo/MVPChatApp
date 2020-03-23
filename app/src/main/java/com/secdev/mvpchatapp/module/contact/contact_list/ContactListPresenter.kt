package com.secdev.mvpchatapp.module.contact.contact_list

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.secdev.mvpchatapp.base.BasePresenter
import com.secdev.mvpchatapp.data.database.Database
import com.secdev.mvpchatapp.data.model.Users

class ContactListPresenter(
    var _view: ContactListContract.ViewInterface? = null
): BasePresenter<ContactListContract.ViewInterface>, ContactListContract.PresenterInterface {

    private var contacts = mutableListOf<Users>()
    private val database = Database()

    override fun onAttach(view: ContactListContract.ViewInterface) {
        _view = view
    }

    override fun onDettach() {
        _view = null
    }

    override fun doGetContacts() {
        val contactRef = database.getContactReference(database.currentUser!!.uid)

        contactRef.keepSynced(true)
        contactRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                _view?.isError(error.message)
            }

            override fun onDataChange(data: DataSnapshot) {
                contacts.clear()
                for (snapshot in data.children) {
                    contacts.add(snapshot.getValue(Users::class.java)!!)
                }

                if (contacts.size > 0) {
                    _view?.isSuccessGetContacts(contacts)
                } else {
                    _view?.contactIsEmpty()
                }
            }
        })
    }
}