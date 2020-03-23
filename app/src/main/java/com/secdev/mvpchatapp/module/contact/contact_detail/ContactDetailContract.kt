package com.secdev.mvpchatapp.module.contact.contact_detail

import com.secdev.mvpchatapp.base.BaseView
import com.secdev.mvpchatapp.data.model.Users

interface ContactDetailContract {
    interface PresenterInterface {
        fun doGetContactDetail(data: Users)
    }

    interface ViewInterface : BaseView {
        fun isSuccessGetContactDetail(data: Users)
        fun isError(message: String)
    }
}