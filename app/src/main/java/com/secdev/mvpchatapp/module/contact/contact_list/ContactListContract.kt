package com.secdev.mvpchatapp.module.contact.contact_list

import com.secdev.mvpchatapp.base.BaseView
import com.secdev.mvpchatapp.data.model.Users

interface ContactListContract {
    interface PresenterInterface {
        fun doGetContacts()
    }
    interface ViewInterface: BaseView {
        fun isSuccessGetContacts(contacts: MutableList<Users>)
        fun contactIsEmpty()
        fun isError(message: String)
    }
}