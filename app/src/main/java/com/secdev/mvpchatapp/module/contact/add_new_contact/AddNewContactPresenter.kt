package com.secdev.mvpchatapp.module.contact.add_new_contact

import com.secdev.mvpchatapp.base.BasePresenter

class AddNewContactPresenter(
    var _view: AddNewContactContract.ViewPresenter? = null
): BasePresenter<AddNewContactContract.ViewPresenter>, AddNewContactContract.PresenterInterface {
    override fun onAttach(view: AddNewContactContract.ViewPresenter) {
        _view = view
    }

    override fun onDettach() {
        _view = null
    }

    override fun AddContactToDatabase(phoneNumber: String) {
        TODO("Not yet implemented")
    }
}