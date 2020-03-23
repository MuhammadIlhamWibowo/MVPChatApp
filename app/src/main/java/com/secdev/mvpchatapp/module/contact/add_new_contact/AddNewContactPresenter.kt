package com.secdev.mvpchatapp.module.contact.add_new_contact

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.secdev.mvpchatapp.base.BasePresenter
import com.secdev.mvpchatapp.data.database.Database
import com.secdev.mvpchatapp.data.model.Users

class AddNewContactPresenter(
    var _view: AddNewContactContract.ViewPresenter? = null
) : BasePresenter<AddNewContactContract.ViewPresenter>, AddNewContactContract.PresenterInterface {

    private val database = Database()

    override fun onAttach(view: AddNewContactContract.ViewPresenter) {
        _view = view
    }

    override fun onDettach() {
        _view = null
    }

    override fun addContactToDatabase(phoneNumber: String) {
        val userReference = database.user.child(phoneNumber)
        userReference.keepSynced(true)
        userReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                _view?.isError(error.message)
            }

            override fun onDataChange(data: DataSnapshot) {
                val userData = data.getValue(Users::class.java)!!

                val contactReference =
                    database.getContactReference(database.currentUser!!.uid).child(phoneNumber)
                contactReference.setValue(userData)
                    .addOnSuccessListener {
                        _view?.isSuccessAddContact("Berhasil menambahkan kontak")
                    }
                    .addOnFailureListener {
                        _view?.isError(it.message!!)
                    }
            }
        })
    }
}