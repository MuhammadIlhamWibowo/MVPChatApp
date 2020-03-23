package com.secdev.mvpchatapp.module.contact.add_new_contact

import com.secdev.mvpchatapp.base.BaseView

interface AddNewContactContract {
    interface PresenterInterface {
        fun AddContactToDatabase(phoneNumber: String)
    }

    interface ViewPresenter : BaseView {
        fun isSuccessAddContact(message: String)
        fun isError(message: String)
    }
}